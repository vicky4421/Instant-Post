package com.vismijatech.instantpost.utils

import android.content.Context
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.UserInput

/**
 * Created by Vivek Sawant on 16-12-2023.
 */

fun getPersonalOrWhatsappUserInput(context: Context): List<UserInput> = listOf(
    UserInput(
        text = null,
        label = context.getString(R.string.personal_recipientName_label),
        expanded = false,
        info = context.getString(R.string.personal_recipientName_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.personal_relationship_label),
        expanded = false,
        info = context.getString(R.string.personal_relationship_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.personal_occasion_label),
        expanded = false,
        info = context.getString(R.string.personal_occasion_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.personal_mainPoints_label),
        expanded = false,
        info = context.getString(R.string.personal_mainPoints_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.personal_qualities_label),
        expanded = false,
        info = context.getString(R.string.personal_qualities_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.personal_tone_label),
        expanded = false,
        info = context.getString(R.string.personal_tone_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.personal_closing_label),
        expanded = false,
        info = context.getString(R.string.personal_closing_info)
    ),
)