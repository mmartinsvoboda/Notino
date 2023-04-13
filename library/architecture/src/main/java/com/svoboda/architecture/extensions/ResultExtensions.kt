package com.svoboda.architecture.extensions

import com.svoboda.architecture.UiState
import com.svoboda.architecture.Result

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
