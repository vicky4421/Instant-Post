package com.vismijatech.instantpost.screens.images.pexel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.db.entities.QueryHistory
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
    private val _queryHistory: MutableStateFlow<List<QueryHistory>> = MutableStateFlow(emptyList())
    val queryHistory: StateFlow<List<QueryHistory>>
        get() = _queryHistory

    val pexelResponse: StateFlow<PexelResponse?>
        get() = pexelRepository.pexelResponse

    private val _loading = MutableStateFlow(value = false)
    val loading: StateFlow<Boolean>
        get() = _loading

    init {
        getPexelResponse()
        fetchAllQueryHistory()
    }

    private fun getPexelResponse(){
        viewModelScope.launch {
            _loading.value = true
            pexelRepository.getPexelResponse()
            _loading.value = false
        }
    }

    fun getSearchImageResponse(query: String){
        viewModelScope.launch {
            _loading.value = true
            pexelRepository.searchImageResponse(imageQuery = query)
            _loading.value = false
        }
    }

    fun insertQueryHistory(queryHistory: QueryHistory){
        viewModelScope.launch {
            pexelRepository.insertQueryHistory(queryHistory = queryHistory)
        }
    }

    fun deleteQueryHistory(queryHistory: QueryHistory){
        viewModelScope.launch {
            pexelRepository.deleteQueryHistory(queryHistory = queryHistory)
        }
    }

    fun fetchAllQueryHistory(){
        viewModelScope.launch {
            pexelRepository.getAllQueryHistory().collect{queryHistory ->
                _queryHistory.value = queryHistory
            }
        }
    }
}