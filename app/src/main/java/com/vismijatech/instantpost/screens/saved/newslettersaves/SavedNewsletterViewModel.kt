package com.vismijatech.instantpost.screens.saved.newslettersaves

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.db.entities.NewsletterContent
import com.vismijatech.instantpost.repository.SavedNewsletterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
@HiltViewModel
class SavedNewsletterViewModel @Inject constructor(private val repository: SavedNewsletterRepository): ViewModel() {
    private val _newsletterContent: MutableStateFlow<List<NewsletterContent>> = MutableStateFlow(
        emptyList()
    )
    val newsletterContent: StateFlow<List<NewsletterContent>>
        get() = _newsletterContent
    init {
        fetchNewsletterContent()
    }
    fun deleteContent(newsletterContent: NewsletterContent){
        viewModelScope.launch {
            repository.deleteNewsletterContent(newsletterContent)
        }
    }
    private fun fetchNewsletterContent() {
        viewModelScope.launch {
            repository.getNewsletterContents().collect{newsletterContents ->
                _newsletterContent.value = newsletterContents
            }
        }
    }
}