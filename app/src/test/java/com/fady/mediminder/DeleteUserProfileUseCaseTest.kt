package com.fady.mediminder

import com.fady.mediminder.domain.repository.MedsRepository
import com.fady.mediminder.domain.usacases.DeleteUserProfileUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

class DeleteUserProfileUseCaseTest {

    private val medsRepository = mock<MedsRepository>()
    private val deleteUserProfileUseCase = DeleteUserProfileUseCase(medsRepository)

    @Test
    fun `invoke calls deleteUserProfile on repository`() = runBlocking {
        deleteUserProfileUseCase.invoke().first()

        verify(medsRepository).deleteUserProfile()
    }
}