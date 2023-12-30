package com.vismijatech.instantpost.utils

import android.content.Context
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.ListItems

/**
 * Created by Vivek Sawant on 30-12-2023.
 */

fun getSavedContentItems(context: Context) = listOf(
    ListItems(
        itemName = context.getString(R.string.dashboardScreen_socialMedia_dashboardItem),
        itemIcon = R.drawable.ic_social_media,
        itemBackgroundColor = null,
        textColor = null
    ),
    ListItems(
        itemName = context.getString(R.string.dashboardScreen_whatsapp_dashboardItem),
        itemIcon = R.drawable.ic_whatsapp,
        itemBackgroundColor = null,
        textColor = null
    ),
    ListItems(
        itemName = context.getString(R.string.dashboardScreen_blog_dashboardItem),
        itemIcon = R.drawable.ic_blog,
        itemBackgroundColor = null,
        textColor = null
    ),
    ListItems(
        itemName = context.getString(R.string.whatsappScreen_personal_whatsappItem),
        itemIcon = R.drawable.ic_person,
        itemBackgroundColor = null,
        textColor = null
    ),
    ListItems(
        itemName = context.getString(R.string.whatsappScreen_business_whatsappItem),
        itemIcon = R.drawable.ic_business,
        itemBackgroundColor = null,
        textColor = null
    ),
    ListItems(
        itemName = context.getString(R.string.whatsappScreen_promotional_whatsappItem),
        itemIcon = R.drawable.ic_promotional,
        itemBackgroundColor = null,
        textColor = null
    ),
    ListItems(
        itemName = context.getString(R.string.whatsappScreen_newsletter_whatsappItem),
        itemIcon = R.drawable.ic_newsletter,
        itemBackgroundColor = null,
        textColor = null
    )
)