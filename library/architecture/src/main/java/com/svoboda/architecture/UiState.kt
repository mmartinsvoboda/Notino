package com.svoboda.architecture

/**
 * A sealed class representing the different states of a UI component.
 *
 * @param T The type of data associated with the UI state.
 */
sealed class UiState<out T> private constructor() {

    /** Represents the loading state of a UI component. */
    object Loading : UiState<Nothing>()

    /** Represents the success state of a UI component with an optional empty value. */
    data class SuccessEmpty<T>(val value: T? = null) : UiState<T>()

    /** Represents the success state of a UI component with a non-empty value. */
    data class Success<T>(val value: T) : UiState<T>()

    /** Represents the failure state of a UI component with an optional message and value. */
    data class Failure<T>(val message: String?, val value: T?) : UiState<T>()

    /** Represents the error state of a UI component with an associated [Throwable] and an optional value. */
    data class Error<T>(val throwable: Throwable, val value: T?) : UiState<T>()

    // Helper functions to handle each UI state case

    /**
     * Executes the [block] if this is the [Loading] state.
     */
    inline fun onLoading(block: () -> Unit): UiState<T> = apply {
        if (this is Loading) block()
    }

    /**
     * Executes the [block] if this is the [SuccessEmpty] state.
     */
    inline fun onSuccessEmpty(block: () -> Unit): UiState<T> = apply {
        if (this is SuccessEmpty<*>) block()
    }

    /**
     * Executes the [block] with the associated value if this is the [Success] state.
     */
    inline fun onSuccess(block: (value: T) -> Unit): UiState<T> = apply {
        if (this is Success) block(value)
    }

    /**
     * Executes the [block] with the associated message and value if this is the [Failure] state.
     */
    inline fun onFailure(block: (message: String?, value: T?) -> Unit): UiState<T> = apply {
        if (this is Failure) block(message, value)
    }

    /**
     * Executes the [block] with the associated [Throwable] and value if this is the [Error] state.
     */
    inline fun onError(block: (throwable: Throwable, value: T?) -> Unit): UiState<T> = apply {
        if (this is Error) block(throwable, value)
    }
}

