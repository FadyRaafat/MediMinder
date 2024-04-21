package com.fady.mediminder

import com.fady.mediminder.domain.repository.MedsRepository
import com.fady.mediminder.domain.usacases.GetUserProfileUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class GetUserProfileUseCaseTest {

    private val medsRepository = mock<MedsRepository>()
    private val getUserProfileUseCase = GetUserProfileUseCase(medsRepository)

    @Test
    fun `invoke calls getUserProfile on repository`(): Unit = runBlocking {
        getUserProfileUseCase.invoke().first()

        verify(medsRepository).getUserProfile()
    }
}