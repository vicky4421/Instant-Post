package com.vismijatech.instantpost.screens.saved.socialmediasaves

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vismijatech.instantpost.components.SavedContentCard
import com.vismijatech.instantpost.db.entities.SocialMediaContent
import com.vismijatech.instantpost.utils.convertIntToDate

/**
 * Created by Vivek Sawant on 29-12-2023.
 */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SavedSocialMediaScreen(navController: NavHostController) {
    val viewModel: SavedSocialViewModel = hiltViewModel()
    val contentState : State<List<SocialMediaContent>> = viewModel.socialMediaContents.collectAsState()
    Surface(
    ) {
        LazyColumn{
            items(contentState.value){socialMediaContent ->
                SavedContentCard(
                    date = convertIntToDate(socialMediaContent.date),
                    content = socialMediaContent.content
                )
            }
        }
    }
}