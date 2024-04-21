package com.fady.mediminder.presentation.components.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fady.mediminder.R
import com.fady.mediminder.presentation.theme.dimen_16
import com.fady.mediminder.presentation.theme.dimen_8

@Composable
fun SignInTitleAndBranding() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painterResource(R.drawable.ic_logo), null
        )
        Spacer(modifier = Modifier.height(dimen_16))
        Text(
            text = stringResource(R.string.auth_login_welcome_title),
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = Modifier.height(dimen_8))
        Text(
            text = stringResource(R.string.auth_login_signin_title),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
    }
}

@Preview
@Composable
private fun SignInAndBrandingPreview() {
    SignInTitleAndBranding()
}
