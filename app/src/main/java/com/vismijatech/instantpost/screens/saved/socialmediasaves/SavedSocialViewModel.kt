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

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading

    private val _isEmpty = MutableStateFlow(value = false)
    val isEmpty: StateFlow<Boolean>
        get() = _isEmpty

    init {
        fetchSocialMediaContents()
    }

    fun deleteContent(socialMediaContent: SocialMediaContent) {
        viewModelScope.launch {
            _loading.value = true
            repository.deleteSocialContent(socialMediaContent = socialMediaContent)
            _loading.value = false
        }
    }
    private fun fetchSocialMediaContents(){
        viewModelScope.launch {
            _loading.value = true
            repository.getSocialMediaContents().collect{socialMediaContentList ->
                _socialMediaContents.value = socialMediaContentList
                _isEmpty.value = socialMediaContentList.isEmpty()
                _loading.value = false
            }
        }
    }
}