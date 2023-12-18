package com.vismijatech.instantpost.screens.blog.blog

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.HollowButton
import com.vismijatech.instantpost.components.InputCard
import com.vismijatech.instantpost.utils.getBlogUserInput

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherBlogScreen(
    navController: NavHostController,
    header: String = stringResource(id = R.string.dashboardScreen_blog_dashboardItem)
) {
    val context = LocalContext.current
    val inputList = getBlogUserInput(context)
    val title = rememberSaveable { mutableStateOf("") }
    val introduction = rememberSaveable { mutableStateOf("") }
    val sections = rememberSaveable { mutableStateOf("") }
    val tone = rememberSaveable { mutableStateOf("") }
    val personalization = rememberSaveable { mutableStateOf("") }
    val callToAction = rememberSaveable { mutableStateOf("") }
    val links = rememberSaveable { mutableStateOf("") }
    val targetAudience = rememberSaveable { mutableStateOf("") }
    val length = rememberSaveable { mutableStateOf("") }
    val seo = rememberSaveable { mutableStateOf("") }
    val data = rememberSaveable { mutableStateOf("") }
    val formatting = rememberSaveable { mutableStateOf("") }
    val keyMessage = rememberSaveable { mutableStateOf("") }
    val conclusion = rememberSaveable { mutableStateOf("") }

    Scaffold {
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
                            stringResource(id = R.string.newsletter_sections_label) -> sections
                            stringResource(id = R.string.newsletter_tone_label) -> tone
                            stringResource(id = R.string.promotional_personalization_label) -> personalization
                            stringResource(id = R.string.socialMedia_callToAction_label) -> callToAction
                            stringResource(id = R.string.newsletter_links_label) -> links
                            stringResource(id = R.string.socialMedia_targetAudience_label) -> targetAudience
                            stringResource(id = R.string.socialMedia_length_label) -> length
                            stringResource(id = R.string.blog_seo_label) -> seo
                            stringResource(id = R.string.blog_supportingData_label) -> data
                            stringResource(id = R.string.blog_formatting_label) -> formatting
                            stringResource(id = R.string.blog_keyMessages_label) -> keyMessage
                            stringResource(id = R.string.blog_conclusion_label) -> conclusion
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

@Preview
@Composable
fun BlogPreview() {
    OtherBlogScreen(navController = rememberNavController(), header = "Blog")
}