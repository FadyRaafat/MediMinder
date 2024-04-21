package com.fady.mediminder.presentation.signin

import com.fady.mediminder.presentation.signin.EmailState


data class LoginStates(
    val emailState: EmailState = EmailState(),
    val loading: Boolean = false,
    )