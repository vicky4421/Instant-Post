package com.vismijatech.instantpost.screens.whatsapp.personal

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
import com.vismijatech.instantpost.model.queries.PersonalQuery
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.utils.getPersonalOrWhatsappUserInput

/**
 * Created by Vivek Sawant on 15-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalMailOrWhatsappScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    header: String
) {
    val context = LocalContext.current
    val inputList = getPersonalOrWhatsappUserInput(context = context)
    val name = rememberSaveable { mutableStateOf("") }
    val relationship = rememberSaveable { mutableStateOf("") }
    val occasion = rememberSaveable { mutableStateOf("") }
    val purpose = rememberSaveable { mutableStateOf("") }
    val mainPoints = rememberSaveable { mutableStateOf("") }
    val qualities = rememberSaveable { mutableStateOf("") }
    val tone = rememberSaveable { mutableStateOf("") }
    val closing = rememberSaveable { mutableStateOf("") }

    Scaffold(topBar = {
        UserInputTopAppBar(header = header, navController = navController)
    }) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                inputList.forEach { userInput ->
                    InputCard(
                        text = when (userInput.label) {
                            stringResource(id = R.string.personal_recipientName_label) -> name
                            stringResource(id = R.string.personal_relationship_label) -> relationship
                            stringResource(id = R.string.personal_occasion_label) -> occasion
                            stringResource(id = R.string.business_purpose_label) -> purpose
                            stringResource(id = R.string.personal_mainPoints_label) -> mainPoints
                            stringResource(id = R.string.personal_qualities_label) -> qualities
                            stringResource(id = R.string.personal_tone_label) -> tone
                            stringResource(id = R.string.personal_closing_label) -> closing
                            else -> throw Exception("User input exception")
                        }, label = userInput.label, info = userInput.info
                    )
                }
                HollowButton(label = stringResource(id = R.string.socialMedia_submit_button)) {
                    val query = "personalQuery"
                    val personalQuery = PersonalQuery(
                        recipientsName = name.value,
                        relationship = relationship.value,
                        occasion = occasion.value,
                        purpose = purpose.value,
                        mainPoints = mainPoints.value,
                        positiveQualities = qualities.value,
                        emotionalTone = tone.value,
                        closingSentiment = closing.value
                    )
                    sharedViewModel.setPersonalInput(query = personalQuery)
                    navController.navigate(InstantPostScreen.ResultScreen.name + "/$query")
                }
            }
        }
    }
}

