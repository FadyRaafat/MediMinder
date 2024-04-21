package com.fady.mediminder.presentation.navgraph

sealed class Route(val route: String) {

    data object SplashScreen : Route("splash")
    data object HomeScreen : Route("home")
    data object ProfileScreen : Route("favorites")
    data object SignInScreen : Route("SignIn")

}
