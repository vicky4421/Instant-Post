package com.vismijatech.instantpost.screens.whatsapp

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.utils.getWhatsappItems

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@Composable
fun WhatsappScreen(navController: NavHostController) {
    val context = LocalContext.current
    Surface {
        WhatsappList(
            itemList = getWhatsappItems(context = context),
            navController = navController,
            context = context
        )
    }
}