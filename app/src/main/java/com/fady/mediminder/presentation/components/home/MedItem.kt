package com.fady.mediminder.presentation.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.fady.mediminder.domain.models.MedsItem
import com.fady.mediminder.presentation.theme.dimen_16
import com.fady.mediminder.presentation.theme.dimen_8
import com.fady.mediminder.presentation.utils.noRippleClickable

@Composable
fun MedItem(
    medsItem: MedsItem,
    onMedClick: (MedsItem) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(dimen_8)
            .noRippleClickable {
                onMedClick(medsItem)
            },
    ) {
        Column(
            Modifier.padding(dimen_16)
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = medsItem.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
            }
            Text(
                text = medsItem.strength,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            )
            Text(
                text = medsItem.dose,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}




