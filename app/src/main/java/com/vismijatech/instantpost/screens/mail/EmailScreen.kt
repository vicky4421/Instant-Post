package com.vismijatech.instantpost.screens.mail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.UserInputTopAppBar
import com.vismijatech.instantpost.utils.getEmailItems

/**
 * Created by Vivek Sawant on 12-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailScreen(navController: NavHostController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            UserInputTopAppBar(
                header = stringResource(id = R.string.loginScreen_email_label),
                navController = navController
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            EmailList(
                itemList = getEmailItems(context = context),
                navController = navController,
                context = context
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmailScreenPreview() {
    EmailScreen(navController = rememberNavController())
}