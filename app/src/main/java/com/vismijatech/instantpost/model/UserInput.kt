package com.vismijatech.instantpost.model

import androidx.compose.runtime.MutableState

/**
 * Created by Vivek Sawant on 14-12-2023.
 */
data class UserInput(
    val text: MutableState<String>?,
    val label: String,
    var expanded: Boolean,
    val info: String
)
