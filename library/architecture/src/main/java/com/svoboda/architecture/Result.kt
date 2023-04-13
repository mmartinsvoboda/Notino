package com.svoboda.architecture

sealed class Result<out T> private constructor() {
    abstract fun getOrNull(): T?

    companion object {
        fun <T> success(value: T) = Success(value)
        fun <T> failure(message: String? = null, value: T? = null) = Failure(message, value)
        fun <T> error(throwable: Throwable, value: T? = null) = Error(throwable, value)
    }

    data class Success<T>(val value: T) : Result<T>() {
        override fun getOrNull(): T? = value
    }

    data class Failure<T>(val message: String?, val value: T?) : Result<T>() {
        override fun getOrNull(): T? = value
    }

    data class Error<T>(val throwable: Throwable, val value: T?) : Result<T>() {
        override fun getOrNull(): T? = value
    }

    inline fun onSuccess(block: (value: T) -> Unit): Result<T> = apply {
        if (this is Success) block(value)
    }

    inline fun onFailure(block: (message: String?, value: T?) -> Unit): Result<T> = apply {
        if (this is Failure) block(message, value)
    }

    inline fun onError(block: (throwable: Throwable, value: T?) -> Unit): Result<T> = apply {
        if (this is Error) block(throwable, value)
    }
}

/**
 * Map `value` of type [T] stored in [Result] to type [R] using [block]. And keep the type of [Result] to be the same (
 * Success is mapped to success, failure to failure...).
 * @see transform
 */
inline fun <T, R> Result<T>.map(block: (value: T) -> R): Result<R> = when (this) {
    is Result.Success -> Result.success(block(value))
    is Result.Failure -> Result.failure(message, this.value?.let(block))
    is Result.Error -> Result.error(throwable, this.value?.let(block))
}

/**
 * Transforms the [Result.Success] to any other type of [Result]. Note that [Result.Failure] and [Result.Error] are not transformed - just re-mapped.
 * If you only want to map the `value` but keep the type of [Result] use [map] function
 * @see map
 */
inline fun <T, R> Result<T>.transform(block: (value: T) -> Result<R>): Result<R> {
    return when (this) {
        is Result.Success -> block(value)
        is Result.Failure -> Result.failure(this.message)
        is Result.Error -> Result.error(this.throwable)
    }
}

/**
 * Combine result [r1] with result [r2] by using [map] function.
 * [map] function is applied if and only if both [r1] and [r2] are [Result.Success].
 * If one of the passed results is [Result.error] or [Result.failure] resulting result will be also [Result.error] or [Result.failure]
 * Result [r1] has higher priority in error/failure resolving - its [Result.Error.throwable]/[Result.Failure.message] is propagated directly if it is
 * not [Result.Success]. Whether the [r2] is [Result.Success], [Result.Failure] or [Result.Error] is solved only if [r1] is [Result.Success]
 */
inline fun <T1, T2, R> combine(r1: Result<T1>, r2: Result<T2>, map: (result1: T1, result2: T2) -> R): Result<R> =
    when (r1) {
        is Result.Error -> Result.error(r1.throwable)
        is Result.Failure -> Result.failure(r1.message)
        is Result.Success -> when (r2) {
            is Result.Error -> Result.error(r2.throwable)
            is Result.Failure -> Result.failure(r2.message)
            is Result.Success -> Result.success(map(r1.value, r2.value))
        }
    }

