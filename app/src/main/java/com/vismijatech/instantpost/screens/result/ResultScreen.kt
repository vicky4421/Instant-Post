package com.vismijatech.instantpost.screens.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.components.LoadingScreen
import com.vismijatech.instantpost.model.openai.Message
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.utils.InputQuery
import com.vismijatech.instantpost.utils.getBlogPrompt
import com.vismijatech.instantpost.utils.getBusinessPrompt
import com.vismijatech.instantpost.utils.getNewsletterPrompt
import com.vismijatech.instantpost.utils.getPersonalPrompt
import com.vismijatech.instantpost.utils.getPromotionalPrompt
import com.vismijatech.instantpost.utils.getSocialMediaPrompt
import com.vismijatech.instantpost.utils.getTravelBlogPrompt
import com.vismijatech.instantpost.utils.getWhatsappReplyPrompt

/**
 * Created by Vivek Sawant on 21-12-2023.
 */

@Composable
fun ResultScreen(
    navController: NavHostController,
    query: String?,
    sharedViewModel: SharedViewModel
) {
    val resultViewModel: ResultViewModel = hiltViewModel()
    val resultState : State<Message?> = resultViewModel.response.collectAsState()
    var result by remember {
        mutableStateOf("")
    }
    val prompt = remember{
        when(query){
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

    if (resultViewModel.loading.value) LoadingScreen() else {
        result = resultState.value?.content.toString()
        ResultDisplay(result = result)
    }

    LaunchedEffect(key1 = prompt){
        resultViewModel.getResponse(prompt = prompt)
    }
}