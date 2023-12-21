package com.vismijatech.instantpost.screens.blog.travel

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
import com.vismijatech.instantpost.model.queries.TravelBlogQuery
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.utils.getTravelBlogUserInputs

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelBlogScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    header: String = stringResource(id = R.string.blogScreen_travel_blogItem)
) {
    val context = LocalContext.current
    val inputList = getTravelBlogUserInputs(context = context)
    val title = rememberSaveable { mutableStateOf("") }
    val introduction = rememberSaveable { mutableStateOf("") }
    val destinations = rememberSaveable { mutableStateOf("") }
    val experience = rememberSaveable { mutableStateOf("") }
    val food = rememberSaveable { mutableStateOf("") }
    val tips = rememberSaveable { mutableStateOf("") }
    val reflections = rememberSaveable { mutableStateOf("") }
    val recommendations = rememberSaveable { mutableStateOf("") }
    val conclusion = rememberSaveable { mutableStateOf("") }
    val callToAction = rememberSaveable { mutableStateOf("") }
    val links = rememberSaveable { mutableStateOf("") }
    val tone = rememberSaveable { mutableStateOf("") }
    val personalization = rememberSaveable { mutableStateOf("") }
    val frequency = rememberSaveable { mutableStateOf("") }
    val length = rememberSaveable { mutableStateOf("") }

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
                        text = when (userInput.label) {
                            stringResource(id = R.string.newsletter_title_label) -> title
                            stringResource(id = R.string.blog_introduction_label) -> introduction
                            stringResource(id = R.string.travelBlog_destinations_label) -> destinations
                            stringResource(id = R.string.travelBlog_experience_label) -> experience
                            stringResource(id = R.string.travelBlog_food_label) -> food
                            stringResource(id = R.string.travelBlog_tips_label) -> tips
                            stringResource(id = R.string.travelBlog_reflections_label) -> reflections
                            stringResource(id = R.string.travelBlog_recommendations_label) -> recommendations
                            stringResource(id = R.string.blog_conclusion_label) -> conclusion
                            stringResource(id = R.string.socialMedia_callToAction_label) -> callToAction
                            stringResource(id = R.string.newsletter_links_label) -> links
                            stringResource(id = R.string.newsletter_tone_label) -> tone
                            stringResource(id = R.string.promotional_personalization_label) -> personalization
                            stringResource(id = R.string.newsletter_frequency_label) -> frequency
                            stringResource(id = R.string.socialMedia_length_label) -> length
                            else -> throw Exception("User input exception")
                        }, label = userInput.label, info = userInput.info
                    )
                }
                HollowButton(label = stringResource(id = R.string.socialMedia_submit_button)) {
                    val query = "travelBlogQuery"
                    val travelBlogQuery = TravelBlogQuery(
                        title = title.value,
                        introduction = introduction.value,
                        destinations = destinations.value,
                        travelExperience = experience.value,
                        food = food.value,
                        tips = tips.value,
                        reflections = reflections.value,
                        recommendations = recommendations.value,
                        conclusion = conclusion.value,
                        callToAction = callToAction.value,
                        links = links.value,
                        tone = tone.value,
                        personalization = personalization.value,
                        frequency = frequency.value,
                        length = length.value
                    )
                    sharedViewModel.setTravelBlogInput(query = travelBlogQuery)
                    navController.navigate(InstantPostScreen.ResultScreen.name + "/$query")
                }
            }
        }
    }
}