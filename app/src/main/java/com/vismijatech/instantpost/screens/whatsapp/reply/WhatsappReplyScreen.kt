package com.vismijatech.instantpost.screens.whatsapp.reply

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
import com.vismijatech.instantpost.model.queries.WhatsappReplyQuery
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.utils.InputQuery
import com.vismijatech.instantpost.utils.getWhatsappReplyUserInputList

/**
 * Created by Vivek Sawant on 13-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatsappReplyScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    val replyList = getWhatsappReplyUserInputList(context)
    val message = rememberSaveable { mutableStateOf("") }
    val sendersTone = rememberSaveable { mutableStateOf("") }
    val relationship = rememberSaveable { mutableStateOf("") }
    val goal = rememberSaveable { mutableStateOf("") }
    val tone = rememberSaveable { mutableStateOf("") }
    val occasion = rememberSaveable { mutableStateOf("") }
    val instructions = rememberSaveable { mutableStateOf("") }
    val length = rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            UserInputTopAppBar(
                header = stringResource(id = R.string.whatsappScreen_messageReply_whatsappItem),
                navController = navController
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                replyList.forEach { userInput ->
                    InputCard(
                        text =
                        when (userInput.label) {
                            stringResource(id = R.string.whatsapp_message_label) -> message
                            stringResource(id = R.string.whatsapp_sendersTone_label) -> sendersTone
                            stringResource(id = R.string.whatsapp_relationship_label) -> relationship
                            stringResource(id = R.string.socialMedia_goal_label) -> goal
                            stringResource(id = R.string.whatsapp_tone_label) -> tone
                            stringResource(id = R.string.personal_occasion_label) -> occasion
                            stringResource(id = R.string.whatsapp_instruction_label) -> instructions
                            stringResource(id = R.string.socialMedia_length_label) -> length
                            else -> throw Exception("User input exception")
                        }, label = userInput.label, info = userInput.info
                    )
                }
                HollowButton(label = stringResource(id = R.string.socialMedia_submit_button)) {
                    val query = InputQuery.WhatsappReplyQuery.name
                    val whatsappReplyQuery = WhatsappReplyQuery(
                        messageContent = message.value,
                        sendersTone = sendersTone.value,
                        relationship = relationship.value,
                        goal = goal.value,
                        yourTone = tone.value,
                        occasion = occasion.value,
                        specialInstructions = instructions.value,
                        length = length.value
                    )
                    sharedViewModel.setWhatsappReplyInput(query = whatsappReplyQuery)
                    navController.navigate(route = InstantPostScreen.ResultScreen.name + "/$query")
                }
            }
        }
    }
}