package com.vismijatech.instantpost.screens.mail.promotional

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.screens.whatsapp.promotional.PromotionalMailOrWhatsappScreen

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@Composable
fun PromotionalMailScreen(navController: NavHostController) {
    PromotionalMailOrWhatsappScreen(
        navController = navController,
        header = stringResource(id = R.string.emailScreen_promotional_emailItem)
    )
}