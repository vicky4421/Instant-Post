package com.vismijatech.instantpost.screens.whatsapp.business

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R

/**
 * Created by Vivek Sawant on 13-12-2023.
 */

@Composable
fun WhatsappBusinessScreen(navController: NavHostController) {
    BusinessMailOrWhatsappScreen(
        navController = navController,
        header = stringResource(id = R.string.personal_whatsapp_header)
    )
}