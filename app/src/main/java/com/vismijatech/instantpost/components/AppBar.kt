package com.vismijatech.instantpost.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily

/**
 * Created by Vivek Sawant on 04-12-2023.
 */

@Preview
@Composable
fun TopAppBarPreview() {
    AppBar(
        startIcon = Icons.Default.Menu,
        onEndIconClick = {}
    ) {

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String = stringResource(id = R.string.app_name),
    startIcon: ImageVector = Icons.Default.Menu,
    endIcon: Painter? = painterResource(id = R.drawable.ic_logout),
    onEndIconClick: () -> Unit,
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(fontFamily = PoppinsFontFamily, fontSize = 24.sp)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(
                    imageVector = startIcon,
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = { onEndIconClick() }) {
                if (endIcon != null) {
                    Icon(
                        painter = endIcon,
                        contentDescription = "Logout"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

