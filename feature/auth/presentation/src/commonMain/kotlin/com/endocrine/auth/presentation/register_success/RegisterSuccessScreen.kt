package com.endocrine.auth.presentation.register_success

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import chirp.feature.auth.presentation.generated.resources.Res
import chirp.feature.auth.presentation.generated.resources.account_successfully_created
import chirp.feature.auth.presentation.generated.resources.email_verification_sent_to_x
import chirp.feature.auth.presentation.generated.resources.login
import chirp.feature.auth.presentation.generated.resources.resend_verification_email
import chirp.feature.auth.presentation.generated.resources.resent_verification_email
import com.endocrine.core.designsystem.components.brand.ChirpSuccessIcon
import com.endocrine.core.designsystem.components.buttons.ChirpButton
import com.endocrine.core.designsystem.components.buttons.ChirpButtonStyle
import com.endocrine.core.designsystem.components.layouts.ChirpAdaptiveResultLayout
import com.endocrine.core.designsystem.components.layouts.ChirpSimpleResultLayout
import com.endocrine.core.designsystem.components.layouts.ChirpSnackbarScaffold
import com.endocrine.core.designsystem.theme.ChirpTheme
import com.endocrine.core.presentation.util.ObserveAsEvents
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun RegisterSuccessRoot(
    viewModel: RegisterSuccessViewModel = koinViewModel(),
    onLoginClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            RegisterSuccessEvent.ResendVerificationEmailSuccess -> snackbarHostState.showSnackbar(
                message = getString(Res.string.resent_verification_email)
            )
        }
    }

    RegisterSuccessScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onAction = { action ->
            when(action) {
                RegisterSuccessAction.OnLoginClick -> onLoginClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun RegisterSuccessScreen(
    state: RegisterSuccessState,
    onAction: (RegisterSuccessAction) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    ChirpSnackbarScaffold(
        snackbarHostState = snackbarHostState
    ) {
        ChirpAdaptiveResultLayout {
            ChirpSimpleResultLayout(
                title = stringResource(Res.string.account_successfully_created),
                description = stringResource(
                    Res.string.email_verification_sent_to_x,
                    state.registeredEmail
                ),
                icon = { ChirpSuccessIcon() },
                primaryButton = {
                    ChirpButton(
                        text = stringResource(Res.string.login),
                        onClick = {
                            onAction(RegisterSuccessAction.OnLoginClick)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                secondaryButton = {
                    ChirpButton(
                        text = stringResource(Res.string.resend_verification_email),
                        onClick = {
                            onAction(RegisterSuccessAction.OnResendVerificationEmailClick)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !state.isResendingVerificationEmail,
                        isLoading = state.isResendingVerificationEmail,
                        style = ChirpButtonStyle.SECONDARY
                    )
                },
                secondaryError = state.resendVerificationError?.asString()
            )
        }
    }
}

@Preview
@Composable
private fun RegisterSuccessScreenPreview() {
    ChirpTheme {
        RegisterSuccessScreen(
            state = RegisterSuccessState(),
            onAction = {},
            snackbarHostState = remember { SnackbarHostState() }
        )
    }
}