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
    init {
        fetchPromotionalContent()
    }

    private fun fetchPromotionalContent() {
        viewModelScope.launch {
            repository.getPromotionalContents().collect{promotionalContents ->
                _promotionalContent.value = promotionalContents
            }
        }
    }
}