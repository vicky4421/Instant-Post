package com.vismijatech.instantpost.screens.socialmedia

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.AppBar
import com.vismijatech.instantpost.components.HollowButton
import com.vismijatech.instantpost.components.InputCard
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.utils.getSocialMediaUserInputList

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialMediaCaptionScreen(navController: NavHostController) {
    val context = LocalContext.current
    val inputList = getSocialMediaUserInputList(context = context)
    val imageDesc = rememberSaveable { mutableStateOf("") }
    val emotions = rememberSaveable { mutableStateOf("") }
    val captionStyle = rememberSaveable { mutableStateOf("") }
    val keywords = rememberSaveable { mutableStateOf("") }
    val message = rememberSaveable { mutableStateOf("") }
    val location = rememberSaveable { mutableStateOf("") }
    val callToAction = rememberSaveable { mutableStateOf("") }
    val goal = rememberSaveable { mutableStateOf("") }
    val tagline = rememberSaveable { mutableStateOf("") }
    val length = rememberSaveable { mutableStateOf("") }
    val targetAudience = rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(id = R.string.dashboardScreen_socialMedia_dashboardItem),
                onEndIconClick = {
                    FirebaseAuth.getInstance().signOut()
                    navController.popBackStack()
                    navController.navigate(InstantPostScreen.LoginScreen.name)
                },
                startIcon = Icons.Default.Home
            ) {
                navController.popBackStack()
                navController.navigate(InstantPostScreen.DashboardScreen.name)
            }
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                inputList.forEach { userInput ->
                    InputCard(
                        text =
                        when (userInput.label) {
                            stringResource(R.string.socialMedia_imageDesc_label) -> imageDesc
                            stringResource(R.string.socialMedia_emotions_label) -> emotions
                            stringResource(R.string.socialMedia_captionStyle_label) -> captionStyle
                            stringResource(R.string.socialMedia_keywords_label) -> keywords
                            stringResource(R.string.socialMedia_message_label) -> message
                            stringResource(R.string.socialMedia_location_label) -> location
                            stringResource(R.string.socialMedia_callToAction_label) -> callToAction
                            stringResource(R.string.socialMedia_goal_label) -> goal
                            stringResource(R.string.socialMedia_tagline_label) -> tagline
                            stringResource(R.string.socialMedia_length_label) -> length
                            stringResource(R.string.socialMedia_targetAudience_label) -> targetAudience
                            else -> throw Exception("User input exception")
                        },
                        label = userInput.label,
                        info = userInput.info
                    )
                }
                HollowButton(label = stringResource(id = R.string.socialMedia_submit_button)) {
                    
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSocial() {
    SocialMediaCaptionScreen(navController = rememberNavController())
}