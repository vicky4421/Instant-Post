package com.vismijatech.instantpost.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily

/**
 * Created by Vivek Sawant on 13-12-2023.
 */

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun InputCardPreview() {
    InputCard(
        text = mutableStateOf("Image Desc"),
        label = "Image",
//        expanded = true,
        info = "info",
//        onClick = {}
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun InputCard(
    text: MutableState<String>,
    label: String,
//    expanded: Boolean,
    info: String,
//    onClick: () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        QueryInput(
            imageDesc = text,
            label = label,
            onClick = { expanded = !expanded }
        )
        AnimatedVisibility(
            visible = expanded,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = info,
                    style = TextStyle(
                        fontFamily = PoppinsFontFamily, fontSize = 16.sp,
                        color = Color.LightGray,
                    )
                )
            }
        }
    }
}