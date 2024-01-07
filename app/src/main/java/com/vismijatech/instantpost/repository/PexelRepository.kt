package com.vismijatech.instantpost.repository

import android.util.Log
import com.vismijatech.instantpost.db.database.InstantPostDatabase
import com.vismijatech.instantpost.db.entities.QueryHistory
import com.vismijatech.instantpost.di.annotations.PexelRetrofitQualifier
import com.vismijatech.instantpost.model.imageapi.pexel.PexelResponse
import com.vismijatech.instantpost.network.pexelapi.PexelApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 04-01-2024.
 */
class PexelRepository @Inject constructor(
    @PexelRetrofitQualifier private val pexelApi: PexelApi,
    private val db: InstantPostDatabase
) {
    private val queryHistoryDao = db.queryHistoryDao()

    private val _pexelResponse = MutableStateFlow<PexelResponse?>(value = null)
    val pexelResponse: StateFlow<PexelResponse?>
        get() = _pexelResponse

    suspend fun getPexelResponse(){
        try {
            val response = pexelApi.getPexelImages()
            if (response.isSuccessful && response.body() != null){
                if (response.code() == 200){
                    response.body().let { pexelResponse ->
                        _pexelResponse.emit(pexelResponse)
                    }
                } else {
                    Log.d("Pexel Repository Error", "getPexelResponse: ${response.errorBody().toString()}")
                }
            }
        } catch (e: Exception){
            Log.d("Pexel Repository", "getPexelResponse: ${e.message}")
            e.printStackTrace()
        }
    }

    suspend fun searchImageResponse(imageQuery: String){
        try {
            val response = pexelApi.searchImage(query = imageQuery)
            if (response.isSuccessful && response.body() != null){
                if (response.code() == 200){
                    response.body().let { pexelResponse ->
                        _pexelResponse.emit(value = pexelResponse)
                    }
                } else {
                    Log.d("Pexel Repository Error", "searchImageResponse: ${response.errorBody().toString()}")
                }
            }
        } catch (e: Exception){
            Log.d("Pexel Repository", "searchImageResponse: ${e.message}")
        }
    }

    suspend fun insertQueryHistory(queryHistory: QueryHistory){
        queryHistoryDao.insertQueryHistory(queryHistory = queryHistory)
    }

    suspend fun deleteQueryHistory(queryHistory: QueryHistory){
        queryHistoryDao.deleteQueryHistory(queryHistory = queryHistory)
    }

    suspend fun getAllQueryHistory() = queryHistoryDao.getAllQueryHistory()
}