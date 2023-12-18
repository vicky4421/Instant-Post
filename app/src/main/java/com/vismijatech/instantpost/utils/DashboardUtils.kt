package com.vismijatech.instantpost.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.Color
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.ListItems
import com.vismijatech.instantpost.model.NavigationDrawerItem

/**
 * Created by Vivek Sawant on 04-12-2023.
 */

fun getNavDrawerItems(context: Context): List<NavigationDrawerItem>{
    return listOf<NavigationDrawerItem>(
        NavigationDrawerItem(
            title = context.getString(R.string.dashboardScreen_rateUs_navDrawerItem),
            selectedIcon = Icons.Filled.Star,
            unSelectedIcon = Icons.Outlined.Star,
            imageDescription = "Rate us",
            onClick = {}
        ),
        NavigationDrawerItem(
            title = context.getString(R.string.dashboardScreen_contactUs_navDrawerItem),
            selectedIcon = Icons.Filled.Email,
            unSelectedIcon = Icons.Outlined.Email,
            imageDescription = "Contact us",
            onClick = {}
        ),
        NavigationDrawerItem(
            title = context.getString(R.string.dashboardScreen_termsOfUse_navDrawerItem),
            selectedIcon = Icons.Filled.Info,
            unSelectedIcon = Icons.Outlined.Info,
            imageDescription = "Terms of use",
            onClick = {}
        ),
        NavigationDrawerItem(
            title = context.getString(R.string.dashboardScreen_howToUse_navDrawerItem),
            selectedIcon = Icons.Filled.Send,
            unSelectedIcon = Icons.Outlined.Send,
            imageDescription = "How to use",
            onClick = {}
        )
    )
}

fun openPlayStoreForRating(context: Context) {
    val uri = Uri.parse("market://details?id=${context.packageName}")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}

fun sendEmail(context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf("vismijatech@gmail.com"))
        putExtra(Intent.EXTRA_SUBJECT, "Contact Us")
    }
    context.startActivity(intent)
}

fun openHowToUseBlogPage(context: Context) {
    val blogUrl = "https://instantpostblog.blogspot.com/2023/11/welcome-to-instant-post-your-ultimate.html"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(blogUrl))
    context.startActivity(intent)
}

fun openTermsOfUseBlogPage(context: Context) {
    val blogUrl = "https://instantpostblog.blogspot.com/2023/12/terms-and-conditions-of-use.html"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(blogUrl))
    context.startActivity(intent)
}

fun getDashboardItems(context: Context): List<ListItems>{
    return listOf<ListItems>(
        ListItems(
            itemName = context.getString(R.string.dashboardScreen_socialMedia_dashboardItem),
            itemIcon = R.drawable.ic_social_media,
            itemBackgroundColor = Color(color = 0xFF4267B2),
            textColor = Color.White
        ),
        ListItems(
            itemName = context.getString(R.string.dashboardScreen_whatsapp_dashboardItem),
            itemIcon = R.drawable.ic_whatsapp,
            itemBackgroundColor = Color(color = 0xFF25D366),
            textColor = Color.White
        ),
        ListItems(
            itemName = context.getString(R.string.loginScreen_email_label),
            itemIcon = R.drawable.ic_email,
            itemBackgroundColor = Color(color = 0xFFBB001B),
            textColor = Color(color = 0xFFFBBc05)
        ),
        ListItems(
            itemName = context.getString(R.string.dashboardScreen_blog_dashboardItem),
            itemIcon = R.drawable.ic_blog,
            itemBackgroundColor = Color(color = 0xFF00749C),
            textColor = Color.White
        )
    )
}