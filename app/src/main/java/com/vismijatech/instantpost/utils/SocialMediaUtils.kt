package com.vismijatech.instantpost.utils

import android.content.Context
import androidx.compose.runtime.MutableState
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.UserInput

/**
 * Created by Vivek Sawant on 14-12-2023.
 */

fun getSocialMediaUserInputList(context: Context): List<UserInput> = listOf(
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_imageDesc_label),
        expanded = false,
        info = context.getString(R.string.socialMedia_imageDesc_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_emotions_label),
        expanded = false,
        info = context.getString(R.string.socialMedia_emotions_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_captionStyle_label),
        expanded = false,
        info = context.getString(R.string.socialMedia_captionStyle_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_keywords_label),
        expanded = false,
        info = context.getString(R.string.socialMedia_keywords_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_message_label),
        expanded = false,
        info = context.getString(R.string.socialMedia_message_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_location_label),
        expanded = false,
        info = context.getString(R.string.socialMedia_location_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_callToAction_label),
        expanded = false,
        info = context.getString(R.string.socialMedia_callToAction_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_goal_label),
        expanded = false,
        info = context.getString(R.string.socialMedia_goal_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_tagline_label),
        expanded = false,
        info = context.getString(R.string.socialMedia_tagline_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_length_label),
        expanded = false,
        info = context.getString(R.string.socialMedia_length_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_targetAudience_label),
        expanded = false,
        info = context.getString(R.string.socialMedia_targetAudience_info)
    )
)