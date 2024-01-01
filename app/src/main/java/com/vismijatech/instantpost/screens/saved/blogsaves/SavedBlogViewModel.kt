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

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading

    private val _isEmpty = MutableStateFlow(value = false)
    val isEmpty: StateFlow<Boolean>
        get() = _isEmpty

    init {
        fetchBlogContents()
    }

    fun deleteContent(blogContent: BlogContent){
        viewModelScope.launch {
            _loading.value = true
            repository.deleteBlogContent(blogContent = blogContent)
            _loading.value = false
        }
    }
    private fun fetchBlogContents() {
        viewModelScope.launch {
            _loading.value = true
            repository.getBlogContents().collect{blogContents ->
                _blogContents.value = blogContents
                _isEmpty.value = blogContents.isEmpty()
                _loading.value = false
            }
        }
    }
}