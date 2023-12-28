package com.vismijatech.instantpost.screens.saved

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.vismijatech.instantpost.components.AppBar
import com.vismijatech.instantpost.components.BottomBar
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.screens.dashboard.ItemList
import com.vismijatech.instantpost.utils.getDashboardItems

/**
 * Created by Vivek Sawant on 28-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedContentScreen(navController: NavHostController) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppBar(onEndIconClick = { }, endIcon = null){ }
        },
        bottomBar = { BottomBar(navController = navController, initialIndex = 2)}
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            ItemList(
                itemList = getDashboardItems(context = context),
                navController = navController,
                context = context
            )
        }
    }
}

@Preview
@Composable
fun SavedPreview() {
    SavedContentScreen(navController = rememberNavController())
}