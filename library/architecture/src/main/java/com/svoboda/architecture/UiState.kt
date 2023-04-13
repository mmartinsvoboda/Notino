package com.svoboda.architecture

sealed class UiState<out T> private constructor() {

    object Loading : UiState<Nothing>()
    data class SuccessEmpty<T>(val value: T? = null) : UiState<T>()
    data class Success<T>(val value: T) : UiState<T>()
    data class Failure<T>(val message: String?, val value: T?) : UiState<T>()
    data class Error<T>(val throwable: Throwable, val value: T?) : UiState<T>()

    inline fun onLoading(block: () -> Unit): UiState<T> = apply {
        if (this is Loading) block()
    }

    inline fun onSuccessEmpty(block: () -> Unit): UiState<T> = apply {
        if (this is SuccessEmpty<*>) block()
    }

    inline fun onSuccess(block: (value: T) -> Unit): UiState<T> = apply {
        if (this is Success) block(value)
    }

    inline fun onFailure(block: (message: String?, value: T?) -> Unit): UiState<T> = apply {
        if (this is Failure) block(message, value)
    }

    inline fun onError(block: (throwable: Throwable, value: T?) -> Unit): UiState<T> = apply {
        if (this is Error) block(throwable, value)
    }
}
