package com.vismijatech.instantpost.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by Vivek Sawant on 15-12-2023.
 */

@Preview
@Composable
fun FabPreview() {
    Fab(icon = Icons.Outlined.Send){}
}

@Composable
fun Fab(icon: ImageVector, onClick: () -> Unit) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    ExtendedFloatingActionButton(
        text = { Text(text = "Submit") },
        icon = { Icon(imageVector = icon, contentDescription = "Submit") },
        expanded = expanded,
        onClick = {
            expanded = !expanded
            onClick()
        }
    )
}