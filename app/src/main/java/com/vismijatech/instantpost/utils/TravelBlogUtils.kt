package com.vismijatech.instantpost.utils

import android.content.Context
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.UserInput

/**
 * Created by Vivek Sawant on 18-12-2023.
 */

fun getTravelBlogUserInputs(context: Context): List<UserInput> = listOf(
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_title_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_title_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.blog_introduction_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_introduction_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.travelBlog_destinations_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_destinations_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.travelBlog_experience_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_experience_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.travelBlog_food_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_food_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.travelBlog_tips_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_tips_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.travelBlog_reflections_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_reflections_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.travelBlog_recommendations_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_recommendations_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.blog_conclusion_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_conclusion_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_callToAction_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_callToAction_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_links_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_links_info)
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
        info = context.getString(R.string.travelBlog_personalization_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_frequency_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_frequency_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_length_label),
        expanded = false,
        info = context.getString(R.string.travelBlog_length_info)
    )
)