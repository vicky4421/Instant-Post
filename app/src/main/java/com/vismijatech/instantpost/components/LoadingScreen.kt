package com.vismijatech.instantpost.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily

/**
 * Created by Vivek Sawant on 08-12-2023.
 */

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.loadingScreen_loading) +"...",
                style = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Bold)
            )
            LinearProgressIndicator()
        }
    }
}