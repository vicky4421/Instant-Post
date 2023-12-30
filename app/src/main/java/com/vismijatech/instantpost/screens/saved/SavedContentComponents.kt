package com.vismijatech.instantpost.screens.saved

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
 * Created by Vivek Sawant on 29-12-2023.
 */

@Composable
fun SavedContentList(
    itemList: List<ListItems>,
    navController: NavController,
    context: Context
) {
    LazyColumn {
        items(itemList) { item ->
            ItemCard(item = item) {
                when (item.itemName) {
                    context.getString(R.string.dashboardScreen_socialMedia_dashboardItem) -> navController.navigate(
                        InstantPostScreen.SavedSocialMediaScreen.name
                    )

                    context.getString(R.string.dashboardScreen_whatsapp_dashboardItem) -> navController.navigate(
                        InstantPostScreen.SavedWhatsappScreen.name
                    )

                    context.getString(R.string.dashboardScreen_blog_dashboardItem) -> navController.navigate(
                        InstantPostScreen.SavedBlogScreen.name
                    )

                    context.getString(R.string.whatsappScreen_personal_whatsappItem) -> navController.navigate(
                        InstantPostScreen.SavedPersonalScreen.name
                    )

                    context.getString(R.string.whatsappScreen_business_whatsappItem) -> navController.navigate(
                        InstantPostScreen.SavedBusinessScreen.name
                    )

                    context.getString(R.string.whatsappScreen_promotional_whatsappItem) -> navController.navigate(
                        InstantPostScreen.SavedPromotionalScreen.name
                    )

                    context.getString(R.string.whatsappScreen_newsletter_whatsappItem) -> navController.navigate(
                        InstantPostScreen.SavedNewsletterScreen.name
                    )
                }
            }
        }
    }
}
