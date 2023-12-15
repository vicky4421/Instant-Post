package com.vismijatech.instantpost.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily

/**
 * Created by Vivek Sawant on 15-12-2023.
 */

@Composable
fun HollowButton(label: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 60.dp)
            .padding(paddingValues = PaddingValues(10.dp))
    ) {
        Text(
            text = label,
            style = TextStyle(fontFamily = PoppinsFontFamily)
        )
    }
}