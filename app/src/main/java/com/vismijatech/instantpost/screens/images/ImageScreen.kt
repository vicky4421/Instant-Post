package com.vismijatech.instantpost.screens.images

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.AppBar
import com.vismijatech.instantpost.components.BottomBar

/**
 * Created by Vivek Sawant on 13-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            AppBar(
                onEndIconClick = { },
                startIcon = null,
                endIcon = null,
                title = stringResource(id = R.string.imageScreen_title)
            ) { }
        },
        bottomBar = { BottomBar(navController = navController, initialIndex = 1) }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {

        }
    }

}