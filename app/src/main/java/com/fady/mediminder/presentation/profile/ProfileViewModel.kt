package com.fady.mediminder.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fady.mediminder.data.utils.Resource
import com.fady.mediminder.domain.usacases.DeleteUserProfileUseCase
import com.fady.mediminder.domain.usacases.GetUserProfileUseCase
import com.fady.mediminder.presentation.signin.AuthNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val getUserProfileUseCase: GetUserProfileUseCase,
    val deleteUserProfileUseCase: DeleteUserProfileUseCase
) : ViewModel() {

    private val _itemsState = MutableStateFlow(ProfileState())
    val itemsState: StateFlow<ProfileState> = _itemsState.asStateFlow()

    init {
        getUserProfile()
    }

    fun onEvent(event: ProfileEvents) {
        when (event) {
            ProfileEvents.OnLogout -> {
                deleteUseProfile()
            }
        }

    }

    private fun getUserProfile() {
        getUserProfileUseCase().onEach { userProfileData ->
            when (userProfileData) {
                is Resource.Error -> {
                    _itemsState.update {
                        it.copy(
                            loading = false
                        )
                    }
                }

                is Resource.Success -> {
                    _itemsState.update {
                        it.copy(
                            loading = false,
                            data = userProfileData.value
                        )
                    }
                }

                else -> {

                }
            }
        }.launchIn(viewModelScope)
    }


      private fun deleteUseProfile() {
        viewModelScope.launch {
            deleteUserProfileUseCase().collect()
        }
    }



}

