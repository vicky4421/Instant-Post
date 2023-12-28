package com.vismijatech.instantpost.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.AnimatedBorderComposable
import com.vismijatech.instantpost.components.dashboardImageGradient
import com.vismijatech.instantpost.navigation.InstantPostScreen

/**
 * Created by Vivek Sawant on 22-12-2023.
 */

@Preview(showBackground = true)
@Composable
fun ResultDisplayPreview() {
    ResultScreenTopAppBar(navController = rememberNavController())
}

@Composable
fun ResultDisplay(result: String) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            AnimatedBorderComposable(
                brush = dashboardImageGradient,
                shape = RoundedCornerShape(size = 5.dp),
                animationDuration = 100000
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_format_quote),
                        contentDescription = "Quote",
                        modifier = Modifier.rotate(180f),
                        alignment = Alignment.TopStart
                    )
                    SelectionContainer {
                        Text(text = result)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_format_quote),
                        contentDescription = "Quote",
                        alignment = Alignment.BottomEnd,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreenTopAppBar(
    navController: NavController,
    onSaveClick: () -> Unit = {},
    onCopyClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    iconEnabled: Boolean = false
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.resultScreen_title)) },
        navigationIcon = {
            IconButton(
                onClick = { navController.navigate(InstantPostScreen.DashboardScreen.name) },
                enabled = iconEnabled
            ) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home Button")
            }
        },
        actions = {
            IconButton(onClick = { onShareClick() }, enabled = iconEnabled) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "Share Button")
            }
            IconButton(onClick = { onCopyClick() }, enabled = iconEnabled) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = "Save Button"
                )
            }
            IconButton(onClick = { onSaveClick() }, enabled = iconEnabled) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = "Copy Button"
                )
            }
        }
    )
}