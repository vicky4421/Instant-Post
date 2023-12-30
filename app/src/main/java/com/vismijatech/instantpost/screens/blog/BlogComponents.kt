package com.vismijatech.instantpost.screens.blog

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
fun BlogList(
    itemList: List<ListItems>,
    navController: NavController,
    context: Context
) {
    LazyColumn {
        items(itemList) { item ->
            ItemCard(item = item) {
                when (item.itemName) {
                    context.getString(R.string.dashboardScreen_blog_dashboardItem) -> navController.navigate(
                        InstantPostScreen.OtherBlogScreen.name
                    )

                    context.getString(R.string.blogScreen_travel_blogItem) -> navController.navigate(
                        InstantPostScreen.TravelBlogScreen.name
                    )
                }
            }
        }
    }
}