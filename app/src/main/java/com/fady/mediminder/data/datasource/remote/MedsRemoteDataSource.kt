package com.fady.mediminder.data.datasource.remote

import com.fady.mediminder.data.datasource.remote.service.ClientService
import com.fady.mediminder.data.utils.BaseRemoteDataSource
import javax.inject.Inject

class MedsRemoteDataSource @Inject constructor(
    private val apiService: ClientService
) : BaseRemoteDataSource() {

    suspend fun getMeds() = safeApiCall {
        apiService.getMeds().mapToDomainModel()
    }
}