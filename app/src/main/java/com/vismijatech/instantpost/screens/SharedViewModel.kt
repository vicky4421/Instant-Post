package com.vismijatech.instantpost.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vismijatech.instantpost.model.queries.BusinessQuery
import com.vismijatech.instantpost.model.queries.PersonalQuery
import com.vismijatech.instantpost.model.queries.PromotionalQuery
import com.vismijatech.instantpost.model.queries.SocialMediaQuery
import com.vismijatech.instantpost.model.queries.WhatsappReplyQuery

/**
 * Created by Vivek Sawant on 19-12-2023.
 */
class SharedViewModel : ViewModel() {

    // query states

    private val _socialQuery: MutableState<SocialMediaQuery?> = mutableStateOf(null)
    val socialQuery: State<SocialMediaQuery?> = _socialQuery

    private val _personalQuery: MutableState<PersonalQuery?> = mutableStateOf(null)
    val personalQuery: State<PersonalQuery?> = _personalQuery

    private val _whatsappReplyQuery: MutableState<WhatsappReplyQuery?> = mutableStateOf(null)
    val whatsappReplyQuery: State<WhatsappReplyQuery?> = _whatsappReplyQuery

    private val _businessQuery: MutableState<BusinessQuery?> = mutableStateOf(null)
    val businessQuery: State<BusinessQuery?> = _businessQuery

    private val _promotionalQuery: MutableState<PromotionalQuery?> = mutableStateOf(null)
    val promotionalQuery : State<PromotionalQuery?> = _promotionalQuery

    // query setter functions

    fun setSocialMediaInput(query: SocialMediaQuery) {
        _socialQuery.value = query
    }

    fun setPersonalInput(query: PersonalQuery) {
        _personalQuery.value = query
    }

    fun setWhatsappReplyInput(query: WhatsappReplyQuery){
        _whatsappReplyQuery.value = query
    }

    fun setBusinessInput(query: BusinessQuery){
        _businessQuery.value = query
    }

    fun setPromotionalInput(query: PromotionalQuery){
        _promotionalQuery.value = query
    }
}