package com.vismijatech.instantpost.utils.firebaseresource

/**
 * Created by Vivek Sawant on 30-11-2023.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T>(data: T? = null): Resource<T>(data)
}