package com.fady.mediminder.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fady.mediminder.data.utils.Resource
import com.fady.mediminder.domain.usacases.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    var shouldGoToSignIn: Boolean? by mutableStateOf(null)
        private set

    init {
        getAccountData()
    }


    private fun getAccountData() {
        getUserProfileUseCase().onEach { result ->
            shouldGoToSignIn = when (result) {
                is Resource.Success -> {
                    result.value == null
                }

                else -> {
                    false
                }
            }
        }.launchIn(viewModelScope)
    }
}
