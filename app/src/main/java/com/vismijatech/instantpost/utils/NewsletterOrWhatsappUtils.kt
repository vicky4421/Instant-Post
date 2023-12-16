package com.vismijatech.instantpost.utils

import android.content.Context
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.UserInput

/**
 * Created by Vivek Sawant on 16-12-2023.
 */

fun getNewsletterOrWhatsappUserInputList(context: Context): List<UserInput> = listOf(
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_theme_label),
        expanded = false,
        info = context.getString(R.string.newsletter_theme_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_title_label),
        expanded = false,
        info = context.getString(R.string.newsletter_title_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_sections_label),
        expanded = false,
        info = context.getString(R.string.newsletter_sections_info)
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
        info = context.getString(R.string.newsletter_links_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.promotional_emailSubjectLine_label),
        expanded = false,
        info = context.getString(R.string.promotional_emailSubjectLine_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_tone_label),
        expanded = false,
        info = context.getString(R.string.newsletter_tone_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.promotional_personalization_label),
        expanded = false,
        info = context.getString(R.string.newsletter_personalization_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.newsletter_frequency_label),
        expanded = false,
        info = context.getString(R.string.newsletter_frequency_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_length_label),
        expanded = false,
        info = context.getString(R.string.newsletter_length_info)
    ),
)