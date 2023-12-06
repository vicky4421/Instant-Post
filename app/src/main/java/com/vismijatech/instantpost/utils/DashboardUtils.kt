package com.vismijatech.instantpost.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Star
import com.vismijatech.instantpost.model.NavigationDrawerItem

/**
 * Created by Vivek Sawant on 04-12-2023.
 */

val navigationDrawerItems = listOf<NavigationDrawerItem>(
    NavigationDrawerItem(
        title = "Rate us",
        selectedIcon = Icons.Filled.Star,
        unSelectedIcon = Icons.Outlined.Star,
        imageDescription = "Rate us"
    ),
    NavigationDrawerItem(
        title = "Contact us",
        selectedIcon = Icons.Filled.Email,
        unSelectedIcon = Icons.Outlined.Email,
        imageDescription = "Contact us"
    ),
    NavigationDrawerItem(
        title = "Terms of use",
        selectedIcon = Icons.Filled.Info,
        unSelectedIcon = Icons.Outlined.Info,
        imageDescription = "Terms of use"
    ),
    NavigationDrawerItem(
        title = "How to use",
        selectedIcon = Icons.Filled.Send,
        unSelectedIcon = Icons.Outlined.Send,
        imageDescription = "How to use"
    ),
    NavigationDrawerItem(
        title = "Delete account",
        selectedIcon = Icons.Filled.AccountCircle,
        unSelectedIcon = Icons.Outlined.AccountCircle,
        imageDescription = "Delete account"
    )
)