package com.fady.mediminder.presentation.home

import com.fady.mediminder.domain.models.MedsItem


data class HomeDataState(
    val medList: List<MedsItem> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
