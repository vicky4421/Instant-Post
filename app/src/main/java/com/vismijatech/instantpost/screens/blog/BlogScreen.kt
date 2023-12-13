package com.vismijatech.instantpost.screens.blog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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