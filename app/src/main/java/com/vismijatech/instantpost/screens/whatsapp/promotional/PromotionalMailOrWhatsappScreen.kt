package com.vismijatech.instantpost.screens.whatsapp.promotional

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
import com.vismijatech.instantpost.model.queries.PromotionalQuery
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.utils.InputQuery
import com.vismijatech.instantpost.utils.getPromotionalMailOrWhatsappUserInputList

/**
 * Created by Vivek Sawant on 16-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromotionalMailOrWhatsappScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    header: String
) {
    val context = LocalContext.current
    val inputList = getPromotionalMailOrWhatsappUserInputList(context = context)
    val audience = rememberSaveable { mutableStateOf("") }
    val product = rememberSaveable { mutableStateOf("") }
    val sellingPoints = rememberSaveable { mutableStateOf("") }
    val callToAction = rememberSaveable { mutableStateOf("") }
    val discounts = rememberSaveable { mutableStateOf("") }
    val subjectLine = rememberSaveable { mutableStateOf("") }
    val personalization = rememberSaveable { mutableStateOf("") }
    val tone = rememberSaveable { mutableStateOf("") }
    val closing = rememberSaveable { mutableStateOf("") }
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
                            stringResource(id = R.string.socialMedia_targetAudience_label) -> audience
                            stringResource(id = R.string.promotional_product_label) -> product
                            stringResource(id = R.string.promotional_keyPoints_label) -> sellingPoints
                            stringResource(id = R.string.socialMedia_callToAction_label) -> callToAction
                            stringResource(id = R.string.promotional_discounts_label) -> discounts
                            stringResource(id = R.string.promotional_emailSubjectLine_label) -> subjectLine
                            stringResource(id = R.string.promotional_personalization_label) -> personalization
                            stringResource(id = R.string.promotional_emailTone_label) -> tone
                            stringResource(id = R.string.personal_closing_label) -> closing
                            stringResource(id = R.string.socialMedia_length_label) -> length
                            else -> throw Exception("User input exception")
                        }, label = userInput.label, info = userInput.info
                    )
                }
                HollowButton(label = stringResource(id = R.string.socialMedia_submit_button)) {
                    val query = InputQuery.PromotionalQuery.name
                    val promotionalQuery = PromotionalQuery(
                        targetAudience = audience.value,
                        product = product.value,
                        keyPoints = sellingPoints.value,
                        callToAction = callToAction.value,
                        discounts = discounts.value,
                        subjectLine = subjectLine.value,
                        personalization = personalization.value,
                        tone = tone.value,
                        closing = closing.value,
                        length = length.value
                    )
                    sharedViewModel.setPromotionalInput(query = promotionalQuery)
                    navController.navigate(InstantPostScreen.ResultScreen.name + "/$query")
                }
            }
        }
    }
}