package com.vismijatech.instantpost.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Star
import com.vismijatech.instantpost.model.NavigationDrawerItem

/**
 * Created by Vivek Sawant on 04-12-2023.
 */

val navigationDrawerItems = listOf<NavigationDrawerItem>(
    NavigationDrawerItem(
        title = "Rate us",
        selectedIcon = Icons.Filled.Star,
        unSelectedIcon = Icons.Outlined.Star,
        imageDescription = "Rate us",
        onClick = {}
    ),
    NavigationDrawerItem(
        title = "Contact us",
        selectedIcon = Icons.Filled.Email,
        unSelectedIcon = Icons.Outlined.Email,
        imageDescription = "Contact us",
        onClick = {}
    ),
    NavigationDrawerItem(
        title = "Terms of use",
        selectedIcon = Icons.Filled.Info,
        unSelectedIcon = Icons.Outlined.Info,
        imageDescription = "Terms of use",
        onClick = {}
    ),
    NavigationDrawerItem(
        title = "How to use",
        selectedIcon = Icons.Filled.Send,
        unSelectedIcon = Icons.Outlined.Send,
        imageDescription = "How to use",
        onClick = {}
    ),
    NavigationDrawerItem(
        title = "Delete account",
        selectedIcon = Icons.Filled.AccountCircle,
        unSelectedIcon = Icons.Outlined.AccountCircle,
        imageDescription = "Delete account",
        onClick = {}
    )
)

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