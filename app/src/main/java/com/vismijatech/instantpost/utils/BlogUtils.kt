package com.vismijatech.instantpost.utils

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.ListItems
import com.vismijatech.instantpost.model.UserInput

/**
 * Created by Vivek Sawant on 13-12-2023.
 */

fun getBlogItems(context: Context): List<ListItems> = listOf(
    ListItems(
        itemName = context.getString(R.string.dashboardScreen_blog_dashboardItem),
        itemIcon = R.drawable.ic_blog,
        itemBackgroundColor = null,
        textColor = null
    ),
    ListItems(
        itemName = context.getString(R.string.blogScreen_travel_blogItem),
        itemIcon = R.drawable.ic_travel,
        itemBackgroundColor = null,
        textColor = null
    ),
)

fun getBlogUserInput(context: Context): List<UserInput> = listOf(
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_title_label),
        expanded = false,
        info = context.getString(R.string.blog_title_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.blog_introduction_label),
        expanded = false,
        info = context.getString(R.string.blog_introduction_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_sections_label),
        expanded = false,
        info = context.getString(R.string.blog_sections_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_tone_label),
        expanded = false,
        info = context.getString(R.string.blog_tone_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.promotional_personalization_label),
        expanded = false,
        info = context.getString(R.string.promotional_personalization_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_callToAction_label),
        expanded = false,
        info = context.getString(R.string.promotional_callToAction_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_links_label),
        expanded = false,
        info = context.getString(R.string.blog_links_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_targetAudience_label),
        expanded = false,
        info = context.getString(R.string.blog_targetAudience_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_length_label),
        expanded = false,
        info = context.getString(R.string.blog_length_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.blog_seo_label),
        expanded = false,
        info = context.getString(R.string.blog_seo_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.blog_supportingData_label),
        expanded = false,
        info = context.getString(R.string.blog_supportingData_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.blog_formatting_label),
        expanded = false,
        info = context.getString(R.string.blog_formatting_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.blog_keyMessages_label),
        expanded = false,
        info = context.getString(R.string.blog_keyMessages_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.blog_conclusion_label),
        expanded = false,
        info = context.getString(R.string.blog_conclusion_info)
    )
)