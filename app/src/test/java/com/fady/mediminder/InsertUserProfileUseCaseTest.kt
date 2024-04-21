package com.fady.mediminder

import com.fady.mediminder.domain.repository.MedsRepository
import com.fady.mediminder.domain.usacases.InsertUserProfileUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class InsertUserProfileUseCaseTest {

    private val medsRepository = mock<MedsRepository>()
    private val insertUserProfileUseCase = InsertUserProfileUseCase(medsRepository)

    @Test
    fun `invoke calls insertUserProfile on repository`() = runBlocking {
        val email = "test@example.com"
        insertUserProfileUseCase.invoke(email).first()

        verify(medsRepository).insertUserProfile(email)
    }
}