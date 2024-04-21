package com.fady.mediminder.data.datasource.remote.service

import com.fady.mediminder.BuildConfig
import com.fady.mediminder.data.dto.MedsDto
import com.fady.mediminder.data.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientService {

    @GET("{apiKey}")
    suspend fun getMeds(
        @Path(API_KEY) apiKey: String = BuildConfig.API_KEY,
    ): MedsDto
}