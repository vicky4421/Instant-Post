package com.vismijatech.instantpost.utils

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.ListItems

/**
 * Created by Vivek Sawant on 13-12-2023.
 */

fun getWhatsappItems(context: Context): List<ListItems> = listOf(
    ListItems(
        itemName = context.getString(R.string.whatsappScreen_personal_whatsappItem),
        itemIcon = R.drawable.ic_whatsapp,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
    ListItems(
        itemName = context.getString(R.string.whatsappScreen_messageReply_whatsappItem),
        itemIcon = R.drawable.ic_whatsapp_reply,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
    ListItems(
        itemName = context.getString(R.string.whatsappScreen_caption_whatsappItem),
        itemIcon = R.drawable.ic_caption,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
    ListItems(
        itemName = context.getString(R.string.whatsappScreen_business_whatsappItem),
        itemIcon = R.drawable.ic_business,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
    ListItems(
        itemName = context.getString(R.string.whatsappScreen_promotional_whatsappItem),
        itemIcon = R.drawable.ic_promotional,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
    ListItems(
        itemName = context.getString(R.string.whatsappScreen_newsletter_whatsappItem),
        itemIcon = R.drawable.ic_newsletter,
        itemBackgroundColor = Color.White,
        textColor = Color.Black
    ),
)