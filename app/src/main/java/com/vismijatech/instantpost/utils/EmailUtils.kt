package com.vismijatech.instantpost.utils

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.DashboardItems

/**
 * Created by Vivek Sawant on 13-12-2023.
 */

fun getEmailItems(context: Context): List<DashboardItems> = listOf(
    DashboardItems(
        itemName = context.getString(R.string.emailScreen_personal_emailItem),
        itemIcon = R.drawable.ic_email,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
    DashboardItems(
        itemName = context.getString(R.string.emailScreen_business_emailItem),
        itemIcon = R.drawable.ic_email,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
    DashboardItems(
        itemName = context.getString(R.string.emailScreen_promotional_emailItem),
        itemIcon = R.drawable.ic_email,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
    DashboardItems(
        itemName = context.getString(R.string.emailScreen_newsletter_emailItem),
        itemIcon = R.drawable.ic_email,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
)