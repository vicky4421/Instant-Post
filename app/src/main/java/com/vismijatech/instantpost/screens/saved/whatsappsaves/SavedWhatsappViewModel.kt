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

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading

    private val _isEmpty = MutableStateFlow(value = false)
    val isEmpty: StateFlow<Boolean>
        get() = _isEmpty

    init {
        fetchWhatsappContents()
    }
    fun deleteContent(whatsappContent: WhatsappContent){
        viewModelScope.launch {
            _loading.value = true
            repository.deleteWhatsappContent(whatsappContent)
            _loading.value = false
        }
    }
    private fun fetchWhatsappContents(){
        viewModelScope.launch {
            _loading.value = true
            repository.getWhatsappContents().collect{whatsappContents ->
                _whatsappContents.value = whatsappContents
                _isEmpty.value = whatsappContents.isEmpty()
                _loading.value = false
            }
        }
    }
}