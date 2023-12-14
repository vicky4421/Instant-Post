package com.vismijatech.instantpost.screens.socialmedia

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.InputCard

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@Composable
fun SocialMediaCaptionScreen(navController: NavHostController) {
    val imageDesc = rememberSaveable {
        mutableStateOf("")
    }
    var imageDescExpanded by rememberSaveable {
        mutableStateOf(value = false)
    }
    Column(modifier = Modifier.padding(16.dp)) {
        InputCard(
            text = imageDesc,
            label = stringResource(id = R.string.socialMedia_imageDesc_label),
            expanded = imageDescExpanded,
            info = stringResource(id = R.string.socialMedia_imageDesc_info)
        ) {
            imageDescExpanded = !imageDescExpanded
        }
    }
}

@Preview
@Composable
fun PreviewSocial() {
    SocialMediaCaptionScreen(navController = rememberNavController())
}