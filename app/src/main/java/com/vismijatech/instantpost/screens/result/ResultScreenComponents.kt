package com.vismijatech.instantpost.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.AnimatedBorderComposable
import com.vismijatech.instantpost.components.dashboardImageGradient

/**
 * Created by Vivek Sawant on 22-12-2023.
 */

@Preview(showBackground = true)
@Composable
fun ResultDisplayPreview() {
    ResultDisplay("ok")
}

@Composable
fun ResultDisplay(result: String) {
    Surface (
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ){
        AnimatedBorderComposable(
            modifier = Modifier.fillMaxSize(),
            brush = dashboardImageGradient,
            shape = RoundedCornerShape(size = 15.dp),
            animationDuration = 10000
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
                Text(text = result)
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

@Composable
fun ResultScreenTopAppBar() {

}