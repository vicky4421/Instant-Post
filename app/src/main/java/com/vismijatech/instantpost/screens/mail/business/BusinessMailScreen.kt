package com.vismijatech.instantpost.screens.mail.business

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.screens.whatsapp.business.BusinessMailOrWhatsappScreen

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@Composable
fun BusinessMailScreen(navController: NavHostController) {
    BusinessMailOrWhatsappScreen(
        navController = navController,
        header = stringResource(id = R.string.business_header)
    )
}