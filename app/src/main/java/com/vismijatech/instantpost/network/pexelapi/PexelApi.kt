package com.vismijatech.instantpost.network.pexelapi

import com.vismijatech.instantpost.model.imageapi.pexel.PexelResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Vivek Sawant on 04-01-2024.
 */
interface PexelApi {
    @GET("curated?per_page=80")
    suspend fun getPexelImages(): Response<PexelResponse>
}