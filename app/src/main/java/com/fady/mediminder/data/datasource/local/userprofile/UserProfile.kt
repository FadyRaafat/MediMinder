package com.fady.mediminder.data.datasource.local.userprofile

import androidx.room.Entity

@Entity(primaryKeys = ["email"])
data class UserProfile(
    val email: String,
)
