package com.vismijatech.instantpost.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vismijatech.instantpost.model.ListItems
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily

/**
 * Created by Vivek Sawant on 30-12-2023.
 */

@Composable
fun ItemCard(
    item: ListItems,
    bgColor: Color = MaterialTheme.colorScheme.background,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width((Int.MAX_VALUE / 2).dp)
            .padding(bottom = 4.dp, start = 4.dp, end = 4.dp, top = 4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(size = 25.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = item.itemBackgroundColor ?: bgColor
        )
    ) {
        Column (
            modifier = Modifier.fillMaxSize()
                .clickable { onClick() }
        ){
            Text(
                text = item.itemName,
                style = TextStyle(
                    fontFamily = PoppinsFontFamily,
                    fontSize = 24.sp,
                    color = item.textColor ?: textColor
                ),
                modifier = Modifier.padding(8.dp)
            )
            Icon(
                painter = painterResource(id = item.itemIcon),
                contentDescription = "Item icon",
                modifier = Modifier
                    .padding(8.dp)
                    .size(36.dp)
                    .align(Alignment.End),
                tint = item.textColor ?: textColor,
            )
        }
    }
}