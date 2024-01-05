package com.vismijatech.instantpost.model.imageapi.pexel

data class PexelResponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>
)