package com.fady.mediminder.data.repository

import com.fady.mediminder.data.datasource.local.userprofile.UserProfile
import com.fady.mediminder.data.datasource.local.userprofile.UserProfileDao
import com.fady.mediminder.data.datasource.remote.MedsRemoteDataSource
import com.fady.mediminder.data.utils.Resource
import com.fady.mediminder.domain.repository.MedsRepository
import javax.inject.Inject

class MedsRepositoryImpl @Inject constructor(
    private val medsRemoteDataSource: MedsRemoteDataSource,
    private val userProfileDao: UserProfileDao
) : MedsRepository {

    override suspend fun getMeds() = medsRemoteDataSource.getMeds()

    override suspend fun insertUserProfile(email: String) =
        userProfileDao.insertUserProfile(UserProfile(email))


    override suspend fun getUserProfile() = Resource.Success(userProfileDao.getUserProfile())

    override suspend fun deleteUserProfile() = userProfileDao.deleteUserProfile()


}