package com.fady.mediminder.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fady.mediminder.presentation.components.bottomNavigation.MedsBottomNavigation
import com.fady.mediminder.presentation.navgraph.AppGraph
import com.fady.mediminder.presentation.navgraph.Route
import com.fady.mediminder.presentation.theme.mediminderTheme
import com.fady.mediminder.presentation.utils.restartApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var requestSplashRemoved: Boolean? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            requestSplashRemoved == null
        }

        setContent {
            val navController = rememberNavController()
            val currentRoute = navController.currentBackStackEntryAsState().value
            val selectedIndex by remember(currentRoute?.destination?.route) {
                mutableIntStateOf(
                    when (currentRoute?.destination?.route) {
                        Route.HomeScreen.route -> 0
                        Route.ProfileScreen.route -> 1
                        else -> 0
                    }
                )
            }
            mediminderTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    AnimatedVisibility(
                        visible = isMainScreen(currentRoute),
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        MedsBottomNavigation(navController, selectedIndex)
                    }
                }) {
                    AppGraph(startDestination = appStartPoint(),
                        navController = navController,
                        paddingValues = it,
                        onRequestSplashRemoved = {
                            requestSplashRemoved = true
                        },
                        logout = {
                            restartApp()
                        })
                }

            }
        }
    }

    private fun appStartPoint(): Route {
        return if (viewModel.shouldGoToSignIn == true) {
            Route.SignInScreen
        } else {
            Route.HomeScreen
        }
    }


    private fun isMainScreen(navBackStackEntry: NavBackStackEntry?): Boolean {
        return navBackStackEntry?.destination?.route == Route.HomeScreen.route || navBackStackEntry?.destination?.route == Route.ProfileScreen.route
    }
}
