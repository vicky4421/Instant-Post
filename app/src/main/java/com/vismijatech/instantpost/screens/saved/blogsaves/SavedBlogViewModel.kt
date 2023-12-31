package com.vismijatech.instantpost.screens.saved.blogsaves

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.db.entities.BlogContent
import com.vismijatech.instantpost.repository.SavedBlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
@HiltViewModel
class SavedBlogViewModel @Inject constructor(private val repository: SavedBlogRepository): ViewModel() {
    private val _blogContents: MutableStateFlow<List<BlogContent>> = MutableStateFlow(emptyList())
    val blogContents: StateFlow<List<BlogContent>>
        get() = _blogContents

    init {
        fetchBlogContents()
    }

    private fun fetchBlogContents() {
        viewModelScope.launch {
            repository.getBlogContents().collect{blogContents ->
                _blogContents.value = blogContents
            }
        }
    }
}