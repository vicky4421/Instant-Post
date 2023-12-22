package com.vismijatech.instantpost.screens.result

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.utils.InputQuery

/**
 * Created by Vivek Sawant on 21-12-2023.
 */

@Composable
fun ResultScreen(
    navController: NavHostController,
    query: String?,
    sharedViewModel: SharedViewModel
) {
    if (query != null && query == InputQuery.SocialMediaQuery.name) {
        val socialMediaQuery = sharedViewModel.socialQuery.value
        Text(
            text = "ImageDesc: ${socialMediaQuery?.imageDescription} \n" +
                    "length: ${socialMediaQuery?.length} \n" +
                    "query: $query"
        )
    } else if (query != null && query == InputQuery.PersonalQuery.name) {
        val personalQuery = sharedViewModel.personalQuery.value
        Text(
            text = "recipientName: ${personalQuery?.recipientsName} \n" +
                    "closing: ${personalQuery?.closingSentiment}" +
                    "query: $query"
        )
    } else if (query != null && query == InputQuery.WhatsappReplyQuery.name) {
        val whatsappReplyQuery = sharedViewModel.whatsappReplyQuery.value
        Text(
            text = "ImageDesc: ${whatsappReplyQuery?.messageContent} \n" +
                    "length: ${whatsappReplyQuery?.length} \n" +
                    "query: $query"
        )
    } else if (query != null && query == InputQuery.BusinessQuery.name) {
        val businessQuery = sharedViewModel.businessQuery.value
        Text(
            text = "name: ${businessQuery?.recipientsName} \n" +
                    "sign: ${businessQuery?.signature} \n" +
                    "query: $query"
        )
    } else if (query != null && query == InputQuery.PromotionalQuery.name) {
        val promotionalQuery = sharedViewModel.promotionalQuery.value
        Text(
            text = "target audience: ${promotionalQuery?.targetAudience} \n" +
                    "length: ${promotionalQuery?.length} \n" +
                    "query: $query"
        )
    } else if (query != null && query == InputQuery.NewsletterQuery.name) {
        val newsletterQuery = sharedViewModel.newsletterQuery.value
        Text(
            text = "theme topic: ${newsletterQuery?.themeOrTopic} \n" +
                    "length: ${newsletterQuery?.length} \n" +
                    "query: $query"
        )
    } else if (query != null && query == InputQuery.BlogQuery.name) {
        val blogQuery = sharedViewModel.blogQuery.value
        Text(
            text = "title: ${blogQuery?.title} \n" +
                    "conclusion: ${blogQuery?.conclusion} \n" +
                    "query: $query"
        )
    } else if (query != null && query == InputQuery.TravelBlogQuery.name) {
        val travelBlogQuery = sharedViewModel.travelBlogQuery.value
        Text(
            text = "title: ${travelBlogQuery?.title} \n" +
                    "length: ${travelBlogQuery?.length} \n" +
                    "query: $query"
        )
    }
}