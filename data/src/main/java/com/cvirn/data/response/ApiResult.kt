package com.cvirn.data.response

import retrofit2.Response

sealed class ApiResult<out T> {
    data class Success<T>(
        val data: T,
    ) : ApiResult<T>()

    data class Error(
        val message: String,
        val code: Int? = null,
    ) : ApiResult<Nothing>()
}

fun <T> Response<T>.toApiResult(): ApiResult<T> =
    if (this.isSuccessful) {
        val body = this.body()
        if (body != null) {
            ApiResult.Success(body)
        } else {
            ApiResult.Error("Empty Response", this.code())
        }
    } else {
        ApiResult.Error(this.message(), this.code())
    }
