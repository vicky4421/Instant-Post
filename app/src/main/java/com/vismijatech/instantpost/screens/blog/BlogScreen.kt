package com.vismijatech.instantpost.screens.blog

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.UserInputTopAppBar
import com.vismijatech.instantpost.utils.getBlogItems

/**
 * Created by Vivek Sawant on 12-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogScreen(navController: NavHostController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            UserInputTopAppBar(
                header = stringResource(id = R.string.dashboardScreen_blog_dashboardItem),
                navController = navController
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            BlogList(
                itemList = getBlogItems(context = context),
                navController = navController,
                context = context
            )
        }
    }
}

@Preview
@Composable
fun BlogScreenPreview() {
    BlogScreen(navController = rememberNavController())
}