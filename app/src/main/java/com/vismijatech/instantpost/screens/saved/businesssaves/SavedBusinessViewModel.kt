package com.vismijatech.instantpost.screens.saved.businesssaves

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.db.entities.BusinessContent
import com.vismijatech.instantpost.repository.SavedBusinessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
@HiltViewModel
class SavedBusinessViewModel @Inject constructor(private val repository: SavedBusinessRepository): ViewModel() {
    private val _businessContent: MutableStateFlow<List<BusinessContent>> = MutableStateFlow(
        emptyList()
    )
    val businessContent: StateFlow<List<BusinessContent>>
        get() = _businessContent

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading

    private val _isEmpty = MutableStateFlow(value = false)
    val isEmpty: StateFlow<Boolean>
        get() = _isEmpty

    init {
        fetchBusinessContent()
    }
    fun deleteContent(businessContent: BusinessContent){
        viewModelScope.launch {
            _loading.value = true
            repository.deleteBusinessContent(businessContent)
            _loading.value = false
        }
    }
    private fun fetchBusinessContent(){
        viewModelScope.launch {
            _loading.value = true
            repository.getBusinessContents().collect{businessContents ->
                _businessContent.value = businessContents
                _isEmpty.value = businessContents.isEmpty()
                _loading.value = false
            }
        }
    }
}