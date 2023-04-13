package com.svoboda.architecture.extensions

import com.svoboda.architecture.Result
import com.svoboda.architecture.UiState

/**
 * Converts a [Result] to a corresponding [UiState] by applying given predicates for custom empty
 * and failure cases.
 *
 * @param T The type of data associated with the [Result].
 * @param customEmptyPredicate A lambda expression that takes an instance of [T] and returns `true`
 * if the instance represents a custom empty state, `false` otherwise. Defaults to returning `false`.
 * @param customFailurePredicate A lambda expression that takes an instance of [T] and returns `true`
 * if the instance represents a custom failure state, `false` otherwise. Defaults to returning `false`.
 * @return A [UiState] instance that corresponds to the [Result] instance.
 */
inline fun <reified T> Result<T>.toUiState(
    customEmptyPredicate: (T) -> Boolean = { false },
    customFailurePredicate: (T) -> Boolean = { false }
): UiState<T> {
    return when (this) {
        is Result.Success -> {
            when (T::class) {
                Unit::class -> UiState.SuccessEmpty()
                List::class -> {
                    when {
                        this.value is List<*> && this.value.isEmpty() -> UiState.SuccessEmpty()
                        else -> UiState.Success(this.value)
                    }
                }
                else -> {
                    when {
                        customEmptyPredicate(this.value) -> UiState.SuccessEmpty()
                        customFailurePredicate(this.value) -> UiState.Failure(null, this.value)
                        else -> UiState.Success(this.value)
                    }
                }
            }
        }
        is Result.Failure -> UiState.Failure(this.message, this.value)
        is Result.Error -> UiState.Error(this.throwable, this.value)
    }
}
