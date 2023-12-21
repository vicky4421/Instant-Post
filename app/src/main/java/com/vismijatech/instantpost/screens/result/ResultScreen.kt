package com.vismijatech.instantpost.screens.result

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.screens.SharedViewModel

/**
 * Created by Vivek Sawant on 21-12-2023.
 */

@Composable
fun ResultScreen(
    navController: NavHostController,
    query: String?,
    sharedViewModel: SharedViewModel
) {
    if (query != null && query == "socialMediaQuery"){
        val socialMediaQuery = sharedViewModel.socialQuery.value
        Text(text = "ImageDesc: ${socialMediaQuery?.imageDescription} \n" +
                "length: ${socialMediaQuery?.length} \n" +
                "query: $query")
    } else if (query != null && query == "personalQuery"){
        val personalQuery = sharedViewModel.personalQuery.value
        Text(text = "recipientName: ${personalQuery?.recipientsName} \n" +
                "closing: ${personalQuery?.closingSentiment}" +
                "query: $query")
    }
}