package com.fady.mediminder.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fady.mediminder.R
import com.fady.mediminder.presentation.theme.dimen_16
import com.fady.mediminder.presentation.theme.dimen_8
import com.fady.mediminder.presentation.utils.noRippleClickable
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ProfileScreen(
    profileState: StateFlow<ProfileState>,
    onEvent: (ProfileEvents) -> Unit,
    logout: () -> Unit

) {
    val state = profileState.collectAsStateWithLifecycle().value

    Scaffold(modifier = Modifier.fillMaxWidth()) {
        when {
            state.data != null -> {
                Column(modifier = Modifier.padding(it)) {
                    Box(Modifier.padding(dimen_16)) {
                        Column {
                            Text(
                                text = stringResource(id = R.string.email),
                            )
                            Spacer(modifier = Modifier.height(dimen_8))
                            Text(
                                text = state.data.email,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Spacer(modifier = Modifier.height(dimen_16))
                            HorizontalDivider()
                        }
                    }

                    Box(
                        Modifier
                            .padding(dimen_16)
                            .noRippleClickable {
                                onEvent(ProfileEvents.OnLogout)
                                logout()

                            }) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.logout),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(dimen_8))
                            Text(
                                text = stringResource(id = R.string.logout),
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    color = Color(0xFFCE0000)
                                )
                            )
                        }
                    }
                }

            }
        }
    }
}

