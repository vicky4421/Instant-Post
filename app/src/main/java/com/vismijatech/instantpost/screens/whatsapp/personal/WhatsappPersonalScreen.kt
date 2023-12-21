package com.vismijatech.instantpost.screens.whatsapp.personal

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.screens.SharedViewModel

/**
 * Created by Vivek Sawant on 13-12-2023.
 */

@Composable
fun WhatsappPersonalScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    PersonalMailOrWhatsappScreen(
        navController = navController,
        sharedViewModel = sharedViewModel,
        header = stringResource(id = R.string.personal_whatsapp_header)
    )
}