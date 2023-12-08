package com.vismijatech.instantpost.screens.dashboard

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.AnimatedBorderComposable
import com.vismijatech.instantpost.components.dashboardImageGradient
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily
import com.vismijatech.instantpost.utils.navigationDrawerItems
import com.vismijatech.instantpost.utils.openHowToUseBlogPage
import com.vismijatech.instantpost.utils.openPlayStoreForRating
import com.vismijatech.instantpost.utils.openTermsOfUseBlogPage
import com.vismijatech.instantpost.utils.sendEmail
import kotlinx.coroutines.launch

/**
 * Created by Vivek Sawant on 03-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DashboardComponentsPreview() {
    NavigationDrawer(
        context = LocalContext.current,
        image = "",
        name = "Janhavi Sawant",
        credits = "200",
        drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    )
}

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
                                "Rate us" -> openPlayStoreForRating(context = context)
                                "Contact us" -> sendEmail(context = context)
                                "How to use" -> openHowToUseBlogPage(context = context)
                                "Terms of use" -> openTermsOfUseBlogPage(context = context)
                                // TODO: delete account
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
                    text = "Credits: ",
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
        animationDuration = 1000,
        borderWidth = 3.dp
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
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "Loading...",
                style = TextStyle(fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Bold)
            )
            LinearProgressIndicator()
        }
    }
}

