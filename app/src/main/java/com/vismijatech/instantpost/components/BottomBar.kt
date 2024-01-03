package com.vismijatech.instantpost.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.vismijatech.instantpost.utils.BottomBarItems

/**
 * Created by Vivek Sawant on 28-12-2023.
 */

@Composable
fun BottomBar(navController: NavController, initialIndex: Int) {
    val screens = listOf(
        BottomBarItems.Dashboard,
        BottomBarItems.Images,
        BottomBarItems.SavedContent
    )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(value = initialIndex)
    }

    NavigationBar(
        containerColor = Color.Transparent,
        modifier = Modifier.wrapContentHeight()
    ) {
        screens.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route)
                },
                icon = {
                    Image(
                        painter = painterResource(id = if (selectedItemIndex == index) item.selectedIcon else item.unSelectedIcon),
                        contentDescription = "Bottom bar icon"
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}