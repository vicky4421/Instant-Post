package com.vismijatech.instantpost.utils

import android.content.Context
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.UserInput

/**
 * Created by Vivek Sawant on 16-12-2023.
 */

fun getPromotionalMailOrWhatsappUserInputList(context: Context): List<UserInput> = listOf(
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_targetAudience_label),
        expanded = false,
        info = context.getString(R.string.promotional_targetAudience_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.promotional_product_label),
        expanded = false,
        info = context.getString(R.string.promotional_product_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.promotional_keyPoints_label),
        expanded = false,
        info = context.getString(R.string.promotional_keyPoints_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_callToAction_label),
        expanded = false,
        info = context.getString(R.string.promotional_callToAction_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.promotional_discounts_label),
        expanded = false,
        info = context.getString(R.string.promotional_discounts_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.promotional_emailSubjectLine_label),
        expanded = false,
        info = context.getString(R.string.promotional_emailSubjectLine_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.promotional_personalization_label),
        expanded = false,
        info = context.getString(R.string.promotional_personalization_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.promotional_emailTone_label),
        expanded = false,
        info = context.getString(R.string.personal_tone_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.personal_closing_label),
        expanded = false,
        info = context.getString(R.string.personal_closing_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_length_label),
        expanded = false,
        info = context.getString(R.string.promotional_length_info)
    ),
)