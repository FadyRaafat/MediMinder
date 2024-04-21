package com.fady.mediminder.presentation.home

import com.fady.mediminder.domain.models.MedsItem


sealed class HomeEvents {

    data object OnRetry : HomeEvents()
 }
