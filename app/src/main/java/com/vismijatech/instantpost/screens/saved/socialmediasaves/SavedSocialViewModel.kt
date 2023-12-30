package com.vismijatech.instantpost.screens.saved.socialmediasaves

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.db.entities.SocialMediaContent
import com.vismijatech.instantpost.repository.SavedSocialRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 30-12-2023.
 */
@HiltViewModel
class SavedSocialViewModel @Inject constructor(private val repository: SavedSocialRepository) : ViewModel() {
    private val _socialMediaContents: MutableStateFlow<List<SocialMediaContent>> =
        MutableStateFlow(emptyList())

    val socialMediaContents: StateFlow<List<SocialMediaContent>>
        get() = _socialMediaContents

    init {
        fetchSocialMediaContents()
    }

    private fun fetchSocialMediaContents(){
        viewModelScope.launch {
            repository.getSocialMediaContents().collect{socialMediaContentList ->
                _socialMediaContents.value = socialMediaContentList
            }
        }
    }
}