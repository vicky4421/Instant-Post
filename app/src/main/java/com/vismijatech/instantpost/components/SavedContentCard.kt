package com.vismijatech.instantpost.components

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily
import com.vismijatech.instantpost.utils.copyToClipboard
import com.vismijatech.instantpost.utils.shareContent

/**
 * Created by Vivek Sawant on 30-12-2023.
 */

@Preview
@Composable
fun SavedCardPreview() {
    SavedContentCard(
        date = "January 1, 1970, 00:00:00 UTC",
        content = "The integer (dateInt) is assumed to represent a date in seconds since the epoch (January 1, 1970, 00:00:00 UTC).\n" +
                "We convert this integer to a Date object in Java or an Instant object in Kotlin.\n" +
                "We then format this date or instant into a string with the desired format (\"yyyy-MM-dd\" in these examples).",
        context = LocalContext.current
    )
}

@Composable
fun SavedContentCard(date: String, content: String, context: Context) {
    var expanded by remember {
        mutableStateOf(value = false)
    }
    Surface(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(size = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .border(
                    width = 2.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(size = 15.dp)
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = date,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontFamily = PoppinsFontFamily),
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
                )
                Row {
                    IconButton(onClick = { shareContent(content = content, context = context) }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = { copyToClipboard(context = context, text = content) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_copy),
                            contentDescription = "Copy Button"
                        )
                    }
                }
            }
            SelectionContainer {
                Text(
                    text = content,
                    style = TextStyle(fontFamily = PoppinsFontFamily),
                    modifier = Modifier
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        )
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .clickable { expanded = !expanded },
                    maxLines = if (!expanded) 2 else Int.MAX_VALUE
                )
            }
        }
    }
}