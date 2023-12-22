package com.vismijatech.instantpost.screens.socialmedia

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.HollowButton
import com.vismijatech.instantpost.components.InputCard
import com.vismijatech.instantpost.components.UserInputTopAppBar
import com.vismijatech.instantpost.model.queries.SocialMediaQuery
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.utils.InputQuery
import com.vismijatech.instantpost.utils.getSocialMediaUserInputList

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialMediaCaptionScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    header: String = stringResource(id = R.string.dashboardScreen_socialMedia_dashboardItem)
) {
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
            UserInputTopAppBar(header = header, navController = navController)
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
                    val query = InputQuery.SocialMediaQuery.name
                    val socialMediaQuery = SocialMediaQuery(
                        imageDescription = imageDesc.value,
                        emotions = emotions.value,
                        captionStyle = captionStyle.value,
                        keywords = keywords.value,
                        messageOrStory = message.value,
                        location = location.value,
                        callToAction = callToAction.value,
                        goal = goal.value,
                        tagline = tagline.value,
                        length = length.value,
                        targetAudience = targetAudience.value
                    )
                    sharedViewModel.setSocialMediaInput(query = socialMediaQuery)

                    // navigate to result
                    navController.navigate(route = InstantPostScreen.ResultScreen.name + "/$query")

                }
            }
        }
    }
}
