package com.fady.mediminder.presentation.signin

sealed class LoginEvents {
    data class OnEmailChanged(val str: String) : LoginEvents()
    data class OnLoginClick(val str: String) : LoginEvents()
}