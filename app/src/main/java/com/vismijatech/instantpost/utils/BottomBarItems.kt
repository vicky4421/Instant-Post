package com.vismijatech.instantpost.utils

import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.navigation.InstantPostScreen

/**
 * Created by Vivek Sawant on 28-12-2023.
 */
sealed class BottomBarItems(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int
) {
    object Dashboard: BottomBarItems(
        route = InstantPostScreen.DashboardScreen.name,
        title = "Dashboard",
        selectedIcon = R.drawable.ic_home,
        unSelectedIcon = R.drawable.ic_home_outlined
    )

    object Images: BottomBarItems(
        route = InstantPostScreen.ImageScreen.name,
        title = "Images",
        selectedIcon = R.drawable.ic_image_filled,
        unSelectedIcon =R.drawable.ic_image_outlined
    )

    object SavedContent: BottomBarItems(
        route = InstantPostScreen.SavedContentScreen.name,
        title = "Saved Contents",
        selectedIcon = R.drawable.ic_save,
        unSelectedIcon = R.drawable.ic_save_outlined
    )
}
