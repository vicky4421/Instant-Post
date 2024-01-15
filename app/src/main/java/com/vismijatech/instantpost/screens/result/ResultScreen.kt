package com.vismijatech.instantpost.screens.result

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.data.LOW_CREDIT_MESSAGE
import com.vismijatech.instantpost.model.openai.OpenAiResponse
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.screens.LoadingScreen
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.utils.InputQuery
import com.vismijatech.instantpost.utils.copyToClipboard
import com.vismijatech.instantpost.utils.getBlogPrompt
import com.vismijatech.instantpost.utils.getBusinessPrompt
import com.vismijatech.instantpost.utils.getNewsletterPrompt
import com.vismijatech.instantpost.utils.getPersonalPrompt
import com.vismijatech.instantpost.utils.getPromotionalPrompt
import com.vismijatech.instantpost.utils.getSocialMediaPrompt
import com.vismijatech.instantpost.utils.getTravelBlogPrompt
import com.vismijatech.instantpost.utils.getWhatsappReplyPrompt
import com.vismijatech.instantpost.utils.shareContent

/**
 * Created by Vivek Sawant on 21-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    navController: NavHostController,
    query: String?,
    sharedViewModel: SharedViewModel,
) {

    val resultViewModel: ResultViewModel = hiltViewModel()
    val resultState: State<OpenAiResponse?> = resultViewModel.response.collectAsState()
    var result: String by remember { mutableStateOf("") }
    val loading by resultViewModel.loading
    val context = LocalContext.current
    val message by resultViewModel.creditMessage

    val prompt = remember {
        when (query) {
            InputQuery.SocialMediaQuery.name -> getSocialMediaPrompt(query = sharedViewModel.socialQuery.value)
            InputQuery.WhatsappReplyQuery.name -> getWhatsappReplyPrompt(query = sharedViewModel.whatsappReplyQuery.value)
            InputQuery.PersonalQuery.name -> getPersonalPrompt(query = sharedViewModel.personalQuery.value)
            InputQuery.BusinessQuery.name -> getBusinessPrompt(query = sharedViewModel.businessQuery.value)
            InputQuery.PromotionalQuery.name -> getPromotionalPrompt(query = sharedViewModel.promotionalQuery.value)
            InputQuery.NewsletterQuery.name -> getNewsletterPrompt(query = sharedViewModel.newsletterQuery.value)
            InputQuery.BlogQuery.name -> getBlogPrompt(query = sharedViewModel.blogQuery.value)
            InputQuery.TravelBlogQuery.name -> getTravelBlogPrompt(query = sharedViewModel.travelBlogQuery.value)
            else -> throw Exception("Input query not found!")
        }
    }

    LaunchedEffect(key1 = prompt) {
        resultViewModel.getResponse(prompt = prompt)
    }

    Scaffold(
        topBar = {
            ResultScreenTopAppBar(
                navController = navController,
                onSaveClick = {
                    resultViewModel.saveResponse(prompt = prompt)
                    Toast.makeText(context, "Saved!", Toast.LENGTH_LONG).show()
                },
                onCopyClick = { copyToClipboard(context = context, text = result) },
                onShareClick = {
                    shareContent(result, context)
                },
                iconEnabled = !loading
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            if (loading) LoadingScreen()
            else if (message == LOW_CREDIT_MESSAGE ) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                navController.navigate(InstantPostScreen.DashboardScreen.name)
            } else {
                result = resultState.value?.choices?.first()?.message?.content ?: ""
                ResultDisplay(result = result)
                if (message.isNotEmpty()) Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        }
    }
}