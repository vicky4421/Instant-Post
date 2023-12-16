package com.vismijatech.instantpost.screens.whatsapp.newsletter

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
import com.vismijatech.instantpost.utils.getNewsletterOrWhatsappUserInputList

/**
 * Created by Vivek Sawant on 16-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsletterOrWhatsappScreen(
    navController: NavHostController,
    header: String
) {
    val context = LocalContext.current
    val inputList = getNewsletterOrWhatsappUserInputList(context = context)
    val theme = rememberSaveable { mutableStateOf("") }
    val title = rememberSaveable { mutableStateOf("") }
    val sections = rememberSaveable { mutableStateOf("") }
    val callToAction = rememberSaveable { mutableStateOf("") }
    val links = rememberSaveable { mutableStateOf("") }
    val subjectLine = rememberSaveable { mutableStateOf("") }
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
                            stringResource(id = R.string.newsletter_theme_label) -> theme
                            stringResource(id = R.string.newsletter_title_label) -> title
                            stringResource(id = R.string.newsletter_sections_label) -> sections
                            stringResource(id = R.string.socialMedia_callToAction_label) -> callToAction
                            stringResource(id = R.string.newsletter_links_label) -> links
                            stringResource(id = R.string.promotional_emailSubjectLine_label) -> subjectLine
                            stringResource(id = R.string.newsletter_tone_label) -> tone
                            stringResource(id = R.string.promotional_personalization_label) -> personalization
                            stringResource(id = R.string.newsletter_frequency_label) -> frequency
                            stringResource(id = R.string.socialMedia_length_label) -> length
                            else -> throw Exception("User input exception")
                        }, label = userInput.label, info = userInput.info
                    )
                }
                HollowButton(label = stringResource(id = R.string.socialMedia_submit_button)) {

                }
            }
        }
    }
}