package com.vismijatech.instantpost.screens.saved

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily

/**
 * Created by Vivek Sawant on 31-12-2023.
 */

@Composable
fun NoDataScreen(context: Context, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painter = painterResource(id = R.drawable.nothing_here), contentDescription = "No data")

        Text(
            text = context.getString(R.string.noData_imageAttribute),
            style = TextStyle(fontWeight = FontWeight.Light),
            fontFamily = PoppinsFontFamily
        )

        Text(
            text = context.getString(R.string.noData_tapMessage),
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontFamily = PoppinsFontFamily
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoDataPreview() {
    NoDataScreen(LocalContext.current){}
}