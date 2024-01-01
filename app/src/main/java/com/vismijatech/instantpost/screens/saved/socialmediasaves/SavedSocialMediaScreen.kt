package com.vismijatech.instantpost.screens.saved.socialmediasaves

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.ContentScreenTopAppBar
import com.vismijatech.instantpost.screens.LoadingScreen
import com.vismijatech.instantpost.components.SavedContentCard
import com.vismijatech.instantpost.db.entities.SocialMediaContent
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.screens.saved.NoDataScreen
import com.vismijatech.instantpost.utils.convertIntToDate

/**
 * Created by Vivek Sawant on 29-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SavedSocialMediaScreen(navController: NavHostController) {
    val viewModel: SavedSocialViewModel = hiltViewModel()
    val contentState: State<List<SocialMediaContent>> =
        viewModel.socialMediaContents.collectAsState()
    val context = LocalContext.current
    val loading by viewModel.loading.collectAsState()
    val isSocialContentEmpty by viewModel.isEmpty.collectAsState()

    Scaffold(
        topBar = {
            ContentScreenTopAppBar(
                title = stringResource(id = R.string.dashboardScreen_socialMedia_dashboardItem),
                navController = navController
            )
        }
    ) {
        if (loading) LoadingScreen()
        else if (isSocialContentEmpty) NoDataScreen(context = context) { navController.navigate(InstantPostScreen.SocialMediaCaptionScreen.name) }
        else {
            Surface(
                modifier = Modifier.padding(it)
            ) {
                LazyColumn {
                    items(contentState.value) { socialMediaContent ->
                        SavedContentCard(
                            date = convertIntToDate(socialMediaContent.date),
                            content = socialMediaContent.content,
                            context = context
                        ){
                            viewModel.deleteContent(socialMediaContent = socialMediaContent)
                        }
                    }
                }
            }
        }
    }
}