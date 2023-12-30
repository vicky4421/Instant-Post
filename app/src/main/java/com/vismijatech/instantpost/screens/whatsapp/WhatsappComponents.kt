package com.vismijatech.instantpost.screens.whatsapp

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.ItemCard
import com.vismijatech.instantpost.model.ListItems
import com.vismijatech.instantpost.navigation.InstantPostScreen

/**
 * Created by Vivek Sawant on 13-12-2023.
 */

@Composable
fun WhatsappList(
    itemList: List<ListItems>,
    navController: NavController,
    context: Context
) {
    LazyColumn {
        items(itemList) { item ->
            ItemCard(item = item) {
                when (item.itemName) {
                    context.getString(R.string.whatsappScreen_personal_whatsappItem) -> navController.navigate(
                        InstantPostScreen.WhatsappPersonalScreen.name
                    )

                    context.getString(R.string.whatsappScreen_messageReply_whatsappItem) -> navController.navigate(
                        InstantPostScreen.WhatsappReplyScreen.name
                    )

                    context.getString(R.string.whatsappScreen_caption_whatsappItem) -> navController.navigate(
                        InstantPostScreen.WhatsappCaptionScreen.name
                    )

                    context.getString(R.string.whatsappScreen_business_whatsappItem) -> navController.navigate(
                        InstantPostScreen.WhatsappBusinessScreen.name
                    )

                    context.getString(R.string.whatsappScreen_promotional_whatsappItem) -> navController.navigate(
                        InstantPostScreen.WhatsappPromotionalScreen.name
                    )

                    context.getString(R.string.whatsappScreen_newsletter_whatsappItem) -> navController.navigate(
                        InstantPostScreen.WhatsappNewsletterScreen.name
                    )
                }
            }
        }
    }
}