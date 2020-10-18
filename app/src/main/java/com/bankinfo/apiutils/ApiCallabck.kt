package com.bankinfo.apiutils
data class ApiCallabck<out T>(val apiResponseStatus: ApiResponseStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): ApiCallabck<T> = ApiCallabck(apiResponseStatus = ApiResponseStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ApiCallabck<T> =
            ApiCallabck(apiResponseStatus = ApiResponseStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ApiCallabck<T> = ApiCallabck(apiResponseStatus = ApiResponseStatus.LOADING, data = data, message = null)
    }
}