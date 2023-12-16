package com.vismijatech.instantpost.utils

import android.content.Context
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.UserInput

/**
 * Created by Vivek Sawant on 16-12-2023.
 */

fun getBusinessOrWhatsappUserInputList(context: Context): List<UserInput> = listOf(
    UserInput(
        text = null,
        label = context.getString(R.string.business_recipientNameAndTitle_label),
        expanded = false,
        info = context.getString(R.string.personal_recipientName_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.business_purpose_label),
        expanded = false,
        info = context.getString(R.string.business_purpose_label)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.business_backgroundInfo_label),
        expanded = false,
        info = context.getString(R.string.business_backgroundInfo_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.personal_mainPoints_label),
        expanded = false,
        info = context.getString(R.string.personal_mainPoints_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.business_tone_label),
        expanded = false,
        info = context.getString(R.string.personal_tone_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_callToAction_label),
        expanded = false,
        info = context.getString(R.string.business_callToAction_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.personal_closing_label),
        expanded = false,
        info = context.getString(R.string.personal_closing_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.business_signature_label),
        expanded = false,
        info = context.getString(R.string.business_signature_info)
    ),
)