package com.fady.mediminder.presentation.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fady.mediminder.presentation.profile.ProfileState
import com.fady.mediminder.presentation.theme.dimen_16
import com.fady.mediminder.presentation.theme.dimen_8
import com.fady.mediminder.presentation.utils.getGreetingsMessage
import kotlinx.coroutines.flow.StateFlow

@Composable
fun GreetingsItem(
    state: StateFlow<ProfileState>,
) {
    val userState = state.collectAsStateWithLifecycle().value

    Card(
        modifier = Modifier
            .padding(dimen_8)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE5E5E5),
        )
    ) {
        Column(
            Modifier.padding(
                top = dimen_16, start = dimen_16, end = dimen_16, bottom = dimen_16
            )
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = getGreetingsMessage()),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                )
            }
            Text(
                text = userState.data?.email ?: "",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            )
        }

    }

}


