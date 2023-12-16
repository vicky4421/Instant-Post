package com.vismijatech.instantpost.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.vismijatech.instantpost.R

/**
 * Created by Vivek Sawant on 14-12-2023.
 */

@Composable
fun QueryInput(imageDesc: MutableState<String>, label: String, onClick: () -> Unit) {
    InputText(
        valueState = imageDesc,
        label = label,
        imeAction = ImeAction.Done,
        trailingIcon = {
            IconButton(onClick = { onClick() }) {
                Icon(painter = painterResource(id = R.drawable.ic_info), contentDescription = "Info")
            }
        },
        isSingleLine = false
    ) {

    }
}