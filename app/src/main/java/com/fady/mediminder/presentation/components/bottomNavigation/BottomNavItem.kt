package com.fady.mediminder.presentation.components.bottomNavigation

import androidx.compose.ui.graphics.painter.Painter
import com.fady.mediminder.presentation.navgraph.Route

data class BottomNavItem(
    val name: String,
    val route: Route,
    val unSelectedIcon: Painter,
    val selectedIcon: Painter,
    val badgeCount: Int = 0
)
