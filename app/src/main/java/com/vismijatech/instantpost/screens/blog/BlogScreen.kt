package com.vismijatech.instantpost.screens.blog

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vismijatech.instantpost.utils.getBlogItems

/**
 * Created by Vivek Sawant on 12-12-2023.
 */

@Composable
fun BlogScreen(navController: NavHostController) {
    val context = LocalContext.current
    Surface {
        BlogList(
            itemList = getBlogItems(context = context),
            navController = navController,
            context = context
        )
    }
}

@Preview
@Composable
fun BlogScreenPreview() {
    BlogScreen(navController = rememberNavController())
}