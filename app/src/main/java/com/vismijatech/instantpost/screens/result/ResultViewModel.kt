package com.vismijatech.instantpost.screens.result

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.model.openai.OpenAiResponse
import com.vismijatech.instantpost.repository.OpenAiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 23-12-2023.
 */
@HiltViewModel
class ResultViewModel @Inject constructor (private val repository: OpenAiRepository): ViewModel(){
    val response : StateFlow<OpenAiResponse?>
        get() = repository.openAiResponse

    val loading = mutableStateOf(false)

    fun getResponse(prompt: String){
        viewModelScope.launch {
            loading.value = true
            repository.getResponse(prompt = prompt)
            loading.value = false
        }
    }
}