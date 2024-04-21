package com.fady.mediminder.presentation.signin


data class EmailState(
    val email: String = "",
    val isValidEmail: Boolean = false,
)


fun EmailState.updateEmail(email: String): EmailState {
    val isValidEmail = email.isNotBlank() && email.isNotEmpty()
    return copy(
        email = email, isValidEmail = isValidEmail
    )

}