package com.fady.mediminder.presentation.navgraph.main

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fady.mediminder.presentation.home.HomeScreen
import com.fady.mediminder.presentation.home.HomeViewModel
import com.fady.mediminder.presentation.navgraph.Route
import com.fady.mediminder.presentation.profile.ProfileScreen
import com.fady.mediminder.presentation.profile.ProfileViewModel

fun NavGraphBuilder.mainGraph(
    logout: () -> Unit

) {
    composable(route = Route.HomeScreen.route) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        HomeScreen(homeViewModel.itemsState,
            homeViewModel.profileState
        ) { homeViewModel.OnEvent(it) }
    }

    composable(route = Route.ProfileScreen.route) {
        val profileViewModel: ProfileViewModel = hiltViewModel()
        ProfileScreen(
            profileState = profileViewModel.itemsState,
            onEvent = { profileViewModel.onEvent(it) },
            logout = logout
        )
    }

}
