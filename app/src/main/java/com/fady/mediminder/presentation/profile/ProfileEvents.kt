package com.fady.mediminder.presentation.profile


sealed class ProfileEvents {
    data object OnLogout : ProfileEvents()
}