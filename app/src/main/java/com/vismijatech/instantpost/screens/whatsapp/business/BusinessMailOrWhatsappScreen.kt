package com.vismijatech.instantpost.screens.whatsapp.business

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
import com.vismijatech.instantpost.model.queries.BusinessQuery
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.utils.InputQuery
import com.vismijatech.instantpost.utils.getBusinessOrWhatsappUserInputList

/**
 * Created by Vivek Sawant on 16-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessMailOrWhatsappScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    header: String
) {
    val context = LocalContext.current
    val businessInputList = getBusinessOrWhatsappUserInputList(context)
    val name = rememberSaveable { mutableStateOf("") }
    val purpose = rememberSaveable { mutableStateOf("") }
    val backgroundInfo = rememberSaveable { mutableStateOf("") }
    val mainPoints = rememberSaveable { mutableStateOf("") }
    val tone = rememberSaveable { mutableStateOf("") }
    val callToAction = rememberSaveable { mutableStateOf("") }
    val closing = rememberSaveable { mutableStateOf("") }
    val sign = rememberSaveable { mutableStateOf("") }

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
                businessInputList.forEach { userInput ->
                    InputCard(
                        text =
                        when (userInput.label) {
                            stringResource(id = R.string.business_recipientNameAndTitle_label) -> name
                            stringResource(id = R.string.business_purpose_label) -> purpose
                            stringResource(id = R.string.business_backgroundInfo_label) -> backgroundInfo
                            stringResource(id = R.string.personal_mainPoints_label) -> mainPoints
                            stringResource(id = R.string.business_tone_label) -> tone
                            stringResource(id = R.string.socialMedia_callToAction_label) -> callToAction
                            stringResource(id = R.string.personal_closing_label) -> closing
                            stringResource(id = R.string.business_signature_label) -> sign
                            else -> throw Exception("User input exception")
                        },
                        label = userInput.label,
                        info = userInput.info
                    )
                }
                HollowButton(label = stringResource(id = R.string.socialMedia_submit_button)) {
                    val query = InputQuery.BusinessQuery.name
                    val businessQuery = BusinessQuery(
                        recipientsName =  name.value,
                        purpose = purpose.value,
                        backgroundInfo = backgroundInfo.value,
                        mainPoints = mainPoints.value,
                        professionalTone = tone.value,
                        callToAction = callToAction.value,
                        closingSentiment = closing.value,
                        signature = sign.value
                    )
                    sharedViewModel.setBusinessInput(query = businessQuery)
                    navController.navigate(route = InstantPostScreen.ResultScreen.name + "/$query")
                }
            }
        }
    }
}