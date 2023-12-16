package com.vismijatech.instantpost.utils

import android.content.Context
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.UserInput

/**
 * Created by Vivek Sawant on 16-12-2023.
 */

fun getWhatsappReplyUserInputList(context: Context): List<UserInput> = listOf(
    UserInput(
        text = null,
        label = context.getString(R.string.whatsapp_message_label),
        expanded = false,
        info = context.getString(R.string.whatsapp_message_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.whatsapp_sendersTone_label),
        expanded = false,
        info = context.getString(R.string.whatsapp_sendersTone_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.whatsapp_relationship_label),
        expanded = false,
        info = context.getString(R.string.whatsapp_relationship_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_goal_label),
        expanded = false,
        info = context.getString(R.string.whatsapp_goal_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.whatsapp_tone_label),
        expanded = false,
        info = context.getString(R.string.whatsapp_tone_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.personal_occasion_label),
        expanded = false,
        info = context.getString(R.string.whatsapp_occasion_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.whatsapp_instruction_label),
        expanded = false,
        info = context.getString(R.string.whatsapp_instruction_info)
    ),
    UserInput(
        text = null,
        label = context.getString(R.string.socialMedia_length_label),
        expanded = false,
        info = context.getString(R.string.whatsapp_length_info)
    ),
)