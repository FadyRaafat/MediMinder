package com.fady.mediminder.presentation.profile

import com.fady.mediminder.data.datasource.local.userprofile.UserProfile

data class ProfileState(
    val data: UserProfile? = null,
    val loading: Boolean = false,
    val error: String? = null
)