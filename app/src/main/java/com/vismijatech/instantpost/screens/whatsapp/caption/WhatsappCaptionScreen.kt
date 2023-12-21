package com.vismijatech.instantpost.screens.whatsapp.caption

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.screens.socialmedia.SocialMediaCaptionScreen

/**
 * Created by Vivek Sawant on 13-12-2023.
 */

@Composable
fun WhatsappCaptionScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    SocialMediaCaptionScreen(
        navController = navController,
        sharedViewModel = sharedViewModel,
        header = stringResource(id = R.string.dashboardScreen_whatsapp_dashboardItem)
    )
}