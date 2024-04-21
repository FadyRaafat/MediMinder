package com.fady.mediminder.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fady.mediminder.data.utils.Resource
import com.fady.mediminder.domain.usacases.GetMedsUseCase
import com.fady.mediminder.domain.usacases.GetUserProfileUseCase
import com.fady.mediminder.presentation.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getMedsUseCase: GetMedsUseCase,
    val getUserProfileUseCase: GetUserProfileUseCase

) : ViewModel() {


    private val _itemsState = MutableStateFlow(HomeDataState())
    val itemsState: StateFlow<HomeDataState> = _itemsState.asStateFlow()

    private val _profileState = MutableStateFlow(ProfileState())
    val profileState: StateFlow<ProfileState> = _profileState.asStateFlow()

    init {
        getMeds()
        getUserProfile()
    }

    fun OnEvent(homeEvents: HomeEvents) {
        when (homeEvents) {
            HomeEvents.OnRetry -> {
                getMeds()
                getUserProfile()
            }
        }
    }


    private fun getMeds() {
        getMedsUseCase().onStart {
            _itemsState.update {
                it.copy(
                    loading = true
                )
            }
        }.onEach { medsData ->
            when (medsData) {
                is Resource.Failure -> {
                    _itemsState.update {
                        it.copy(
                            loading = false,
                            error = "An unexpected error occurred"
                        )
                    }
                }

                is Resource.Success -> {
                    _itemsState.update {
                        it.copy(
                            medList = medsData.value, loading = false
                        )
                    }
                }

                else -> {}
            }
        }.launchIn(viewModelScope)

    }

    private fun getUserProfile() {
        getUserProfileUseCase().onEach { userProfileData ->
            when (userProfileData) {
                is Resource.Error -> {
                    _profileState.update {
                        it.copy(
                            loading = false
                        )
                    }
                }

                is Resource.Success -> {
                    _profileState.update {
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


}