package com.mbappsoftware.apimarvel.api

interface APIListener<T> {

    fun onSuccess(model: T)

    fun onFailure(str: String)
}