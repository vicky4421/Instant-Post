package com.vismijatech.instantpost.screens.mail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vismijatech.instantpost.utils.getEmailItems

/**
 * Created by Vivek Sawant on 12-12-2023.
 */

@Composable
fun EmailScreen(navController: NavHostController) {
    val context = LocalContext.current
    Surface {
        EmailList(
            itemList = getEmailItems(context = context),
            navController = navController,
            context = context
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmailScreenPreview() {
    EmailScreen(navController = rememberNavController())
}