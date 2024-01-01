package com.vismijatech.instantpost.screens.saved.personalsaves

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.db.entities.PersonalContent
import com.vismijatech.instantpost.repository.SavedPersonalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 31-12-2023.
 */
@HiltViewModel
class SavedPersonalViewModel @Inject constructor(private val repository: SavedPersonalRepository): ViewModel() {
    private val _personalContents: MutableStateFlow<List<PersonalContent>> = MutableStateFlow(
        emptyList()
    )
    val personalContent: StateFlow<List<PersonalContent>>
        get() = _personalContents

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading

    private val _isEmpty = MutableStateFlow(value = false)
    val isEmpty: StateFlow<Boolean>
        get() = _isEmpty


    init {
        fetchPersonalContents()
    }
    fun deleteContent(personalContent: PersonalContent){
        viewModelScope.launch {
            _loading.value = true
            repository.deletePersonalContent(personalContent)
            _loading.value = false
        }
    }
    private fun fetchPersonalContents() {
       viewModelScope.launch {
           _loading.value = true
           repository.getPersonalContents().collect{personalContents ->
               _personalContents.value = personalContents
               _isEmpty.value = personalContents.isEmpty()
               _loading.value = false
           }
       }
    }
}