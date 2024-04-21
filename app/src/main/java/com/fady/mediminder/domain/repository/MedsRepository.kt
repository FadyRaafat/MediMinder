package com.fady.mediminder.domain.repository

import com.fady.mediminder.data.datasource.local.userprofile.UserProfile
import com.fady.mediminder.data.dto.MedsDto
import com.fady.mediminder.data.utils.Resource
import com.fady.mediminder.domain.models.MedsItem

interface MedsRepository {

    suspend fun getMeds(): Resource<List<MedsItem>>

    suspend fun insertUserProfile(email: String)

    suspend fun getUserProfile(): Resource<UserProfile?>

    suspend fun deleteUserProfile()
}