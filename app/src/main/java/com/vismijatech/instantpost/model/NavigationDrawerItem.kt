package com.vismijatech.instantpost.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Vivek Sawant on 04-12-2023.
 */
data class NavigationDrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val imageDescription: String,
    val onClick: () -> Unit
)