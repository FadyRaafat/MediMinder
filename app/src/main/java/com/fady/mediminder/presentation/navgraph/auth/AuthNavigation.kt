package com.fady.mediminder.presentation.navgraph.auth

import androidx.navigation.NavController
import com.fady.mediminder.presentation.navgraph.Route


fun NavController.navigateToHome() {
    navigate(Route.HomeScreen.route) {
        launchSingleTop = true
        popUpTo(this@navigateToHome.graph.id) {
            inclusive = true
        }
    }
}

