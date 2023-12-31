package com.vismijatech.instantpost.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.vismijatech.instantpost.navigation.InstantPostScreen

/**
 * Created by Vivek Sawant on 30-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreenTopAppBar(title: String, navController: NavController) {
    TopAppBar(title = { Text(text = title) }, navigationIcon = {
        IconButton(onClick = { navController.navigate(InstantPostScreen.DashboardScreen.name) }) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home Icon")
        }
    })
}