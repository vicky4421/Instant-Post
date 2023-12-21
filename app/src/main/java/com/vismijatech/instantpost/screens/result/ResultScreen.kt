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
    if (query != null && query == "socialMediaQuery") {
        val socialMediaQuery = sharedViewModel.socialQuery.value
        Text(
            text = "ImageDesc: ${socialMediaQuery?.imageDescription} \n" +
                    "length: ${socialMediaQuery?.length} \n" +
                    "query: $query"
        )
    } else if (query != null && query == "personalQuery") {
        val personalQuery = sharedViewModel.personalQuery.value
        Text(
            text = "recipientName: ${personalQuery?.recipientsName} \n" +
                    "closing: ${personalQuery?.closingSentiment}" +
                    "query: $query"
        )
    } else if (query != null && query == "whatsappReplyQuery") {
        val whatsappReplyQuery = sharedViewModel.whatsappReplyQuery.value
        Text(
            text = "ImageDesc: ${whatsappReplyQuery?.messageContent} \n" +
                    "length: ${whatsappReplyQuery?.length} \n" +
                    "query: $query"
        )
    } else if (query != null && query == "businessQuery") {
        val businessQuery = sharedViewModel.businessQuery.value
        Text(
            text = "name: ${businessQuery?.recipientsName} \n" +
                    "sign: ${businessQuery?.signature} \n" +
                    "query: $query"
        )
    } else if (query != null && query == "promotionalQuery") {
        val promotionalQuery = sharedViewModel.promotionalQuery.value
        Text(
            text = "target audience: ${promotionalQuery?.targetAudience} \n" +
                    "length: ${promotionalQuery?.length} \n" +
                    "query: $query"
        )
    } else if (query != null && query == "newsletterQuery") {
        val newsletterQuery = sharedViewModel.newsletterQuery.value
        Text(
            text = "theme topic: ${newsletterQuery?.themeOrTopic} \n" +
                    "length: ${newsletterQuery?.length} \n" +
                    "query: $query"
        )
    } else if (query != null && query == "blogQuery") {
        val blogQuery = sharedViewModel.blogQuery.value
        Text(
            text = "title: ${blogQuery?.title} \n" +
                    "conclusion: ${blogQuery?.conclusion} \n" +
                    "query: $query"
        )
    } else if (query != null && query == "travelBlogQuery") {
        val travelBlogQuery = sharedViewModel.travelBlogQuery.value
        Text(
            text = "title: ${travelBlogQuery?.title} \n" +
                    "length: ${travelBlogQuery?.length} \n" +
                    "query: $query"
        )
    }
}