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

    init {
        fetchPersonalContents()
    }
    fun deleteContent(personalContent: PersonalContent){
        viewModelScope.launch {
            repository.deletePersonalContent(personalContent)
        }
    }
    private fun fetchPersonalContents() {
       viewModelScope.launch {
           repository.getPersonalContents().collect{personalContents ->
               _personalContents.value = personalContents
           }
       }
    }
}