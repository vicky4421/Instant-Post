package com.vismijatech.instantpost.screens.mail

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
fun EmailList(
    itemList: List<ListItems>,
    navController: NavController,
    context: Context
) {
    LazyColumn {
        items(itemList) { item ->
            ItemCard(item = item) {
                when (item.itemName) {
                    context.getString(R.string.emailScreen_personal_emailItem) -> navController.navigate(
                        InstantPostScreen.PersonalMailScreen.name
                    )

                    context.getString(R.string.emailScreen_business_emailItem) -> navController.navigate(
                        InstantPostScreen.BusinessMailScreen.name
                    )

                    context.getString(R.string.emailScreen_promotional_emailItem) -> navController.navigate(
                        InstantPostScreen.PromotionalMailScreen.name
                    )

                    context.getString(R.string.emailScreen_newsletter_emailItem) -> navController.navigate(
                        InstantPostScreen.NewsletterScreen.name
                    )
                }
            }
        }
    }
}