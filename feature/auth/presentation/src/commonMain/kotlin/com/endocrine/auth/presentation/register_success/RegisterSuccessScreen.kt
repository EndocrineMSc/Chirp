package com.endocrine.auth.presentation.register_success

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import chirp.feature.auth.presentation.generated.resources.Res
import chirp.feature.auth.presentation.generated.resources.account_successfully_created
import chirp.feature.auth.presentation.generated.resources.email_verification_sent_to_x
import chirp.feature.auth.presentation.generated.resources.login
import chirp.feature.auth.presentation.generated.resources.resend_verification_email
import com.endocrine.core.designsystem.components.brand.ChirpBrandLogo
import com.endocrine.core.designsystem.components.brand.ChirpSuccessIcon
import com.endocrine.core.designsystem.components.buttons.ChirpButton
import com.endocrine.core.designsystem.components.buttons.ChirpButtonStyle
import com.endocrine.core.designsystem.components.layouts.ChirpAdaptiveResultLayout
import com.endocrine.core.designsystem.components.layouts.ChirpSimpleSuccessLayout
import com.endocrine.core.designsystem.theme.ChirpTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun RegisterSuccessRoot(
    viewModel: RegisterSuccessViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RegisterSuccessScreen(
        state = state, onAction = viewModel::onAction
    )
}

@Composable
private fun RegisterSuccessScreen(
    state: RegisterSuccessState,
    onAction: (RegisterSuccessAction) -> Unit,
) {
    ChirpAdaptiveResultLayout {
        ChirpSimpleSuccessLayout(
            title = stringResource(Res.string.account_successfully_created),
            description = stringResource(
                Res.string.email_verification_sent_to_x, state.registeredEmail
            ),
            icon = { ChirpSuccessIcon() },
            primaryButton = {
                ChirpButton(
                    text = stringResource(Res.string.login), onClick = {
                        onAction(RegisterSuccessAction.OnLoginClick)
                    }, modifier = Modifier.fillMaxWidth()
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
        )
    }
}

@Preview
@Composable
private fun RegisterSuccessScreenPreview() {
    ChirpTheme {
        RegisterSuccessScreen(
            state = RegisterSuccessState(), onAction = {})
    }
}