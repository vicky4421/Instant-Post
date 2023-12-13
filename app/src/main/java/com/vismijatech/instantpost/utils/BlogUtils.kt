package com.vismijatech.instantpost.utils

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.ListItems

/**
 * Created by Vivek Sawant on 13-12-2023.
 */

fun getBlogItems(context: Context): List<ListItems> = listOf(
    ListItems(
        itemName = context.getString(R.string.dashboardScreen_blog_dashboardItem),
        itemIcon = R.drawable.ic_blog,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
    ListItems(
        itemName = context.getString(R.string.blogScreen_travel_blogItem),
        itemIcon = R.drawable.ic_travel,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
)