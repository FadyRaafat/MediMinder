package com.fady.mediminder.domain.usacases

import com.fady.mediminder.data.utils.Resource
import com.fady.mediminder.domain.models.MedsItem
import com.fady.mediminder.domain.repository.MedsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMedsUseCase @Inject constructor(
    private val medsRepository: MedsRepository,
) {
    operator fun invoke(): Flow<Resource<List<MedsItem>>> = flow {
        emit(Resource.Loading)
        emit(medsRepository.getMeds())
    }.flowOn(Dispatchers.IO)
}