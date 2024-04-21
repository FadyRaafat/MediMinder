package com.fady.mediminder.domain.usacases

import com.fady.mediminder.data.datasource.local.userprofile.UserProfile
import com.fady.mediminder.data.utils.Resource
import com.fady.mediminder.domain.repository.MedsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val medsRepository: MedsRepository,
) {
    operator fun invoke(): Flow<Resource<UserProfile?>> = flow {
        emit(medsRepository.getUserProfile())
    }.flowOn(Dispatchers.IO)
}