package com.vismijatech.instantpost.utils

/**
 * Created by Vivek Sawant on 24-12-2023.
 */
sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)
    class Loading<T>(data: T? = null) : NetworkResult<T>(data)
}
