package com.fady.mediminder.domain.usacases

import com.fady.mediminder.domain.repository.MedsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertUserProfileUseCase @Inject constructor(
    private val medsRepository: MedsRepository,
) {
    operator fun invoke(email: String) = flow {
        emit(medsRepository.insertUserProfile(email))
    }.flowOn(Dispatchers.IO)
}