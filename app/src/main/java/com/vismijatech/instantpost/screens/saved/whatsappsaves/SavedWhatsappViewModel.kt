package com.vismijatech.instantpost.screens.saved.whatsappsaves

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.db.entities.WhatsappContent
import com.vismijatech.instantpost.repository.SavedWhatsappRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
@HiltViewModel
class SavedWhatsappViewModel @Inject constructor(private val repository: SavedWhatsappRepository): ViewModel() {
    private val _whatsappContents: MutableStateFlow<List<WhatsappContent>> = MutableStateFlow(
        emptyList()
    )
    val whatsappContent : StateFlow<List<WhatsappContent>>
        get() = _whatsappContents

    init {
        fetchWhatsappContents()
    }

    private fun fetchWhatsappContents(){
        viewModelScope.launch {
            repository.getWhatsappContents().collect{whatsappContents ->
                _whatsappContents.value = whatsappContents
            }
        }
    }
}