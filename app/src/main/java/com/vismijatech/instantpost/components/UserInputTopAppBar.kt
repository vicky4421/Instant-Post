package com.vismijatech.instantpost.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.vismijatech.instantpost.navigation.InstantPostScreen

/**
 * Created by Vivek Sawant on 16-12-2023.
 */

@Composable
fun UserInputTopAppBar(header: String, navController: NavHostController) {
    AppBar(
        title = header,
        onEndIconClick = {
            FirebaseAuth.getInstance().signOut()
            navController.popBackStack()
            navController.navigate(InstantPostScreen.LoginScreen.name)
        },
        startIcon = Icons.Default.Home
    ) {
        navController.popBackStack()
        navController.navigate(InstantPostScreen.DashboardScreen.name)
    }
}