package com.vismijatech.instantpost.screens.mail.newsletter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.screens.whatsapp.newsletter.NewsletterOrWhatsappScreen

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@Composable
fun NewsletterScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    NewsletterOrWhatsappScreen(
        navController = navController,
        sharedViewModel = sharedViewModel,
        header = stringResource(id = R.string.emailScreen_newsletter_emailItem)
    )
}