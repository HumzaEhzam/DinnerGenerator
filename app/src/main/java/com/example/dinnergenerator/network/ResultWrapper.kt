package com.example.dinnergenerator.network

import com.moveitech.riderapp.dataModel.utilModel.ApiErrorResponse


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ApiErrorResponse? = null): ResultWrapper<Nothing>()
    data object NetworkError: ResultWrapper<Nothing>()
    data object ConnectNetworkError: ResultWrapper<Nothing>()
}