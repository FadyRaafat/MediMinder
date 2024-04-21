package com.fady.mediminder.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fady.mediminder.domain.usacases.InsertUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val insertUserProfileUseCase: InsertUserProfileUseCase
) : ViewModel() {
    private val navigationChannel = Channel<AuthNavigationEvent>()
    val navigationEventsChannelFlow = navigationChannel.receiveAsFlow()

    var loginState = MutableStateFlow(LoginStates())
        private set

    fun onEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.OnEmailChanged -> {
                loginState.update {
                    it.copy(
                        emailState = loginState.value.emailState.updateEmail(event.str)
                    )
                }
            }

            is LoginEvents.OnLoginClick -> {
                insertUserProfile(event.str)
            }
        }
    }

    private fun insertUserProfile(email: String) {
        viewModelScope.launch {
            insertUserProfileUseCase(email).collect()
            navigationChannel.send(AuthNavigationEvent.Continue)
        }
    }


}


