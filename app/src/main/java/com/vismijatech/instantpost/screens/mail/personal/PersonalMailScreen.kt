package com.vismijatech.instantpost.screens.mail.personal

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.screens.whatsapp.personal.PersonalMailOrWhatsappScreen

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@Composable
fun PersonalMailScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    PersonalMailOrWhatsappScreen(
        navController = navController,
        sharedViewModel = sharedViewModel,
        header = stringResource(id = R.string.personal_mail_header)
    )
}