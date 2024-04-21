package com.fady.mediminder.presentation.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fady.mediminder.R
import com.fady.mediminder.presentation.components.auth.SignInTitleAndBranding
import com.fady.mediminder.presentation.theme.dimen_16
import com.fady.mediminder.presentation.theme.dimen_24

@Composable
fun SignInScreen(
    state: LoginStates,
    event: (LoginEvents) -> Unit,
) {
    Scaffold {
        Column(
            Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            SignInTitleAndBranding()

            Spacer(modifier = Modifier.height(dimen_16))
            OutlinedTextField(
                value = state.emailState.email,
                onValueChange = { email ->
                    event(LoginEvents.OnEmailChanged(email))
                },
                label = {
                    Text(
                        text = stringResource(R.string.auth_login_email_hint),
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(dimen_16)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Button(
                onClick = { event(LoginEvents.OnLoginClick(state.emailState.email)) },
                enabled = state.emailState.isValidEmail,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimen_16, vertical = dimen_24),
            ) {
                Text(
                    text = stringResource(R.string.auth_login_button_title),
                )
            }

        }
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {

    SignInScreen(event = {}, state = LoginStates())
}
