package com.vismijatech.instantpost.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vismijatech.instantpost.model.queries.PersonalQuery
import com.vismijatech.instantpost.model.queries.SocialMediaQuery

/**
 * Created by Vivek Sawant on 19-12-2023.
 */
class SharedViewModel : ViewModel() {

    private val _socialQuery: MutableState<SocialMediaQuery?> = mutableStateOf(null)
    val socialQuery: State<SocialMediaQuery?> = _socialQuery

    private val _personalQuery: MutableState<PersonalQuery?> = mutableStateOf(null)
    val personalQuery: State<PersonalQuery?> = _personalQuery

    fun setSocialMediaInput(query: SocialMediaQuery) {
        _socialQuery.value = query
    }

    fun setPersonalInput(query: PersonalQuery) {
        _personalQuery.value = query
    }
}