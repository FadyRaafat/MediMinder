package com.fady.mediminder.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fady.mediminder.domain.models.MedsItem
import com.fady.mediminder.presentation.components.home.DetailedMedItemDialog
import com.fady.mediminder.presentation.components.home.EmptyScreen
import com.fady.mediminder.presentation.components.home.GreetingsItem
import com.fady.mediminder.presentation.components.home.LoadingDialog
import com.fady.mediminder.presentation.components.home.MedItem
import com.fady.mediminder.presentation.profile.ProfileState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(
    state: StateFlow<HomeDataState>,
    profileState: StateFlow<ProfileState>,
    onEvent: (HomeEvents) -> Unit
) {

    val homeState = state.collectAsStateWithLifecycle().value
    val lazyListState = rememberLazyListState()
    val openDialog = remember { mutableStateOf(false) }
    val itemToRemove = remember { mutableStateOf(MedsItem("", "", "")) }

    Scaffold { padding ->
        LoadingDialog(homeState.loading)
        when {
            homeState.medList.isNotEmpty() -> {
                Column(
                    modifier = Modifier.padding(padding),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    GreetingsItem(state = profileState)

                    LazyColumn(state = lazyListState) {
                        itemsIndexed(homeState.medList) { _, med ->
                            MedItem(medsItem = med, onMedClick = {
                                openDialog.value = true
                                itemToRemove.value = it
                            })
                        }
                    }
                }
            }
            homeState.error != null -> {
                EmptyScreen {
                    onEvent(HomeEvents.OnRetry)
                }
            }
        }
        if (openDialog.value) {
            DetailedMedItemDialog(
                onDismiss = {
                    openDialog.value = false
                },
                medsItem = itemToRemove.value
            )
        }
    }

}
