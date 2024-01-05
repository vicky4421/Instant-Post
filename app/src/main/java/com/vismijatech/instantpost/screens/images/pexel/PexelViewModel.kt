package com.vismijatech.instantpost.screens.images.pexel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.model.imageapi.pexel.PexelResponse
import com.vismijatech.instantpost.repository.PexelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 04-01-2024.
 */
@HiltViewModel
class PexelViewModel @Inject constructor(
    private val pexelRepository: PexelRepository,
): ViewModel() {
    val pexelResponse: StateFlow<PexelResponse?>
        get() = pexelRepository.pexelResponse

    private val _loading = MutableStateFlow(value = false)
    val loading: StateFlow<Boolean>
        get() = _loading

    init {
        getPexelResponse()
    }

    private fun getPexelResponse(){
        viewModelScope.launch {
            _loading.value = true
            pexelRepository.getPexelResponse()
            _loading.value = false
        }
    }
}