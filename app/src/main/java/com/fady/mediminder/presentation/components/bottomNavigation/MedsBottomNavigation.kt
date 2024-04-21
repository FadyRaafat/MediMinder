package com.fady.mediminder.presentation.components.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.fady.mediminder.R
import com.fady.mediminder.presentation.navgraph.Route

@Composable
fun MedsBottomNavigation(
    navController: NavController, selectedIndex: Int
) {
    BottomNavigationBar(selectedIndex = selectedIndex, items = listOf(
        BottomNavItem(
            stringResource(id = R.string.home),
            Route.HomeScreen,
            unSelectedIcon = painterResource(id = R.drawable.ic_home),
            selectedIcon = painterResource(id = R.drawable.ic_home_selected),

            ),
        BottomNavItem(
            stringResource(id = R.string.profile),
            Route.ProfileScreen,
            unSelectedIcon = painterResource(id = R.drawable.ic_profile),
            selectedIcon = painterResource(id = R.drawable.ic_profile_selected),
        ),
    ), onItemClick = {
        navController.navigate(it.route.route)
    })
}
