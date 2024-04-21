package com.fady.mediminder.presentation.navgraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fady.mediminder.presentation.navgraph.auth.authGraph
import com.fady.mediminder.presentation.navgraph.main.mainGraph

typealias Lambda = () -> Unit

@Composable
fun AppGraph(
    navController: NavHostController,
    startDestination: Route = Route.SplashScreen,
    paddingValues: PaddingValues,
    onRequestSplashRemoved: Lambda,
    logout: () -> Unit

) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,

        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        authGraph(
            navController
        )
        mainGraph(
            logout = { logout() }
        )
    }
    LaunchedEffect(key1 = Unit) {
        onRequestSplashRemoved()
    }
}
