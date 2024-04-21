package com.fady.mediminder

import com.fady.mediminder.domain.repository.MedsRepository
import com.fady.mediminder.domain.usacases.GetMedsUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class GetMedsUseCaseTest {

    private val medsRepository = mock<MedsRepository>()
    private val getMedsUseCase = GetMedsUseCase(medsRepository)

    @Test
    fun `invoke calls getMeds on repository`(): Unit = runBlocking {
        getMedsUseCase.invoke().first()

        verify(medsRepository).getMeds()
    }
}