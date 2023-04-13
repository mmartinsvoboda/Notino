package com.svoboda.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.svoboda.architecture.UiState
import com.svoboda.ui.theme.LocalNotinoColors

/**
 * A composable function that renders content based on the [UiState] provided.
 *
 * @param uiState The [UiState] to determine which content should be displayed.
 * @param onNonSuccessStateButtonClicked A lambda that is called when the user clicks the button in non-success states.
 * @param onSuccess A @Composable lambda that is called when the [UiState] is [UiState.Success].
 */
@Composable
fun <T> UiStateContent(
    uiState: UiState<T>,
    onNonSuccessStateButtonClicked: () -> Unit,
    onSuccess: @Composable (T) -> Unit
) {
    UiStateContent(
        uiState = uiState,
        onSuccess = onSuccess,
        onError = { _, _ -> DefaultNonSuccessState(onNonSuccessStateButtonClicked) },
        onFailure = { _, _ -> DefaultNonSuccessState(onNonSuccessStateButtonClicked) },
        onSuccessEmpty = { DefaultNonSuccessState(onNonSuccessStateButtonClicked) }
    )
}

/**
 * A composable function that renders content based on the [UiState] provided.
 *
 * @param uiState The [UiState] to determine which content should be displayed.
 * @param onSuccess A @Composable lambda that is called when the [UiState] is [UiState.Success].
 * @param onError A @Composable lambda that is called when the [UiState] is [UiState.Error].
 * @param onFailure A @Composable lambda that is called when the [UiState] is [UiState.Failure].
 * @param onSuccessEmpty A @Composable lambda that is called when the [UiState] is [UiState.SuccessEmpty].
 * @param onLoading A @Composable lambda that is called when the [UiState] is [UiState.Loading].
 */
@Composable
fun <T> UiStateContent(
    uiState: UiState<T>,
    onSuccess: @Composable (T) -> Unit,
    onError: @Composable (Throwable, T?) -> Unit = { _, _ -> },
    onFailure: @Composable (String?, T?) -> Unit = { _, _ -> },
    onSuccessEmpty: @Composable () -> Unit = { },
    onLoading: @Composable () -> Unit = { DefaultLoadingState() }
) {
    when (uiState) {
        is UiState.Success -> onSuccess(uiState.value)
        is UiState.Error -> onError(uiState.throwable, uiState.value)
        is UiState.Failure -> onFailure(uiState.message, uiState.value)
        is UiState.SuccessEmpty -> onSuccessEmpty()
        is UiState.Loading -> onLoading()
    }
}

/**
 * A composable function that displays a loading state with a CircularProgressIndicator.
 */
@Composable
private fun DefaultLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = LocalNotinoColors.current.colors.primary
        )
    }
}

/**
 * A composable function that displays a default non-success state with an error message and a "Try Again" button.
 *
 * @param onNonSuccessStateButtonClicked A lambda that is called when the user clicks the "Try Again" button.
 */
@Composable
private fun DefaultNonSuccessState(
    onNonSuccessStateButtonClicked: () -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.error_apologize))
        Spacer(modifier = Modifier.height(16.dp))
        NotinoButton(onClick = onNonSuccessStateButtonClicked) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}
