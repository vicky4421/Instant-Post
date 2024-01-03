package com.vismijatech.instantpost.screens.dashboard

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.AnimatedBorderComposable
import com.vismijatech.instantpost.components.ItemCard
import com.vismijatech.instantpost.components.dashboardImageGradient
import com.vismijatech.instantpost.model.ListItems
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily
import com.vismijatech.instantpost.utils.getNavDrawerItems
import com.vismijatech.instantpost.utils.openHowToUseBlogPage
import com.vismijatech.instantpost.utils.openPlayStoreForRating
import com.vismijatech.instantpost.utils.openTermsOfUseBlogPage
import com.vismijatech.instantpost.utils.sendEmail
import kotlinx.coroutines.launch

/**
 * Created by Vivek Sawant on 03-12-2023.
 */

// navigation drawer
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(
    context: Context,
    image: String,
    name: String,
    credits: String,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    content: @Composable (drawerState: DrawerState) -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val selectedItemIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    ModalNavigationDrawer(
        modifier = Modifier.fillMaxHeight(),
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                DrawerHeader(context = context, image = image, name = name, credits = credits)
                val navigationDrawerItems = getNavDrawerItems(context = context)
                navigationDrawerItems.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = item.title,
                                style = TextStyle(fontFamily = PoppinsFontFamily)
                            )
                        },
                        selected = index == selectedItemIndex.intValue,
                        onClick = {
                            when (item.title) {
                                context.getString(R.string.dashboardScreen_rateUs_navDrawerItem) -> openPlayStoreForRating(
                                    context = context
                                )

                                context.getString(R.string.dashboardScreen_contactUs_navDrawerItem) -> sendEmail(
                                    context = context
                                )

                                context.getString(R.string.dashboardScreen_howToUse_navDrawerItem) -> openHowToUseBlogPage(
                                    context = context
                                )

                                context.getString(R.string.dashboardScreen_termsOfUse_navDrawerItem) -> openTermsOfUseBlogPage(
                                    context = context
                                )
                            }
                            selectedItemIndex.intValue = index
                            scope.launch { drawerState.close() }

                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex.intValue) item.selectedIcon
                                else item.unSelectedIcon,
                                contentDescription = item.imageDescription
                            )
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        content(drawerState)
    }
}

@Composable
fun DrawerHeader(
    context: Context,
    image: String,
    name: String,
    credits: String
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(width = 120.dp, height = 150.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedBorderImage(context = context, image = image)
            Text(
                text = name,
                style = TextStyle(fontFamily = PoppinsFontFamily),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row {
                Text(
                    text = stringResource(id = R.string.dashboardScreen_credits) + " ",
                    style = TextStyle(fontFamily = PoppinsFontFamily),
                    textAlign = TextAlign.Center,
                    color = Color.Black.copy(alpha = 0.5f)
                )
                Text(
                    text = credits,
                    style = TextStyle(fontFamily = PoppinsFontFamily),
                    textAlign = TextAlign.Center,
                    color = Color.Black.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun AnimatedBorderImage(context: Context, image: String) {
    AnimatedBorderComposable(
        brush = dashboardImageGradient,
        shape = CircleShape,
        borderWidth = 3.dp,
        animationDuration = 1000
    ) {
        ProfileImage(context = context, image = image)
    }
}

@Composable
fun ProfileImage(context: Context, image: String) {
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(data = image)
            .crossfade(enable = true)
            .build(),
        contentDescription = "Profile Image",
        placeholder = painterResource(id = R.drawable.profile_placeholder),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(size = 100.dp)
    )
}

@Composable
fun DashboardItemList(
    itemList: List<ListItems>,
    navController: NavController,
    context: Context
) {
    LazyVerticalGrid(modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp/2), columns = GridCells.Fixed(count = 2), content = {
        items(itemList) { item ->
            ItemCard(item = item) {
                when (item.itemName) {
                    context.getString(R.string.dashboardScreen_socialMedia_dashboardItem) -> navController.navigate(
                        InstantPostScreen.SocialMediaCaptionScreen.name
                    )

                    context.getString(R.string.dashboardScreen_whatsapp_dashboardItem) -> navController.navigate(
                        InstantPostScreen.WhatsappScreen.name
                    )

                    context.getString(R.string.loginScreen_email_label) -> navController.navigate(
                        InstantPostScreen.EmailScreen.name
                    )

                    context.getString(R.string.dashboardScreen_blog_dashboardItem) -> navController.navigate(
                        InstantPostScreen.BlogScreen.name
                    )
                }
            }
        }
    }
    )
}



