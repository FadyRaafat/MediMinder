package com.fady.mediminder.presentation.navgraph.auth

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fady.mediminder.presentation.signin.SignInScreen
import com.fady.mediminder.presentation.signin.SignInViewModel
import com.fady.mediminder.presentation.navgraph.Route
import com.fady.mediminder.presentation.signin.AuthNavigationEvent
import com.fady.mediminder.presentation.utils.ObserveAsEvents

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
) {
    composable(route = Route.SignInScreen.route) {
        val viewModel: SignInViewModel = hiltViewModel()
        ObserveAsEvents(
            flow = viewModel.navigationEventsChannelFlow,
            onEvent = {
                when (it) {
                    AuthNavigationEvent.Continue ->
                        navController.navigateToHome()
                }
            }
        )
        SignInScreen(
            event = { viewModel.onEvent(it) },
            state = viewModel.loginState.collectAsState().value
        )
    }

}
