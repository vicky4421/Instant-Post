package com.vismijatech.instantpost.screens.saved.promotionalsaves

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.db.entities.PromotionalContent
import com.vismijatech.instantpost.repository.SavedPromotionalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
@HiltViewModel
class SavedPromotionalViewModel @Inject constructor(private val repository: SavedPromotionalRepository): ViewModel() {
    private val _promotionalContent: MutableStateFlow<List<PromotionalContent>> = MutableStateFlow(
        emptyList()
    )
    val promotionalContent : StateFlow<List<PromotionalContent>>
        get() = _promotionalContent

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading

    private val _isEmpty = MutableStateFlow(value = false)
    val isEmpty: StateFlow<Boolean>
        get() = _isEmpty

    init {
        fetchPromotionalContent()
    }
    fun deleteContent(promotionalContent: PromotionalContent){
        viewModelScope.launch {
            _loading.value = true
            repository.deletePromotionalContent(promotionalContent)
            _loading.value = false
        }
    }
    private fun fetchPromotionalContent() {
        viewModelScope.launch {
            _loading.value = true
            repository.getPromotionalContents().collect{promotionalContents ->
                _promotionalContent.value = promotionalContents
                _isEmpty.value = promotionalContents.isEmpty()
                _loading.value = false
            }
        }
    }
}