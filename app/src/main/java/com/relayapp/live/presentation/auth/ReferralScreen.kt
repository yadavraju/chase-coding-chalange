package com.relayapp.live.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.relayapp.live.components.CommonEditText
import com.relayapp.live.presentation.base.ExceptionHandleView
import com.relayapp.live.presentation.ui.theme.Green
import com.relayapp.live.presentation.ui.theme.TradeUpColors
import com.relayapp.live.presentation.ui.theme.TradeUpTypography
import com.relayapp.live.presentation.ui.theme.greyButtonColor

@Composable
fun ReferralScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToAuthScreen: () -> Unit
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    val scaffoldState = rememberScaffoldState()
    ExceptionHandleView(
        state = viewState,
        snackBarHostState = scaffoldState.snackbarHostState,
        positiveAction = { _, _ -> }
    ) {
        ReferralScreenRoot(viewModel, navigateToAuthScreen)
    }

}

@Composable
fun ReferralScreenRoot(
    viewModel: AuthViewModel,
    navigateToProfileOnSkipScreen: () -> Unit
) {
    val referralValue = rememberSaveable { mutableStateOf("") }
    val isValidFilled by remember { mutableStateOf(true) }
    val isValidate by remember { derivedStateOf { referralValue.value.isNotBlank() } }
    val keyboardController = LocalTextInputService.current

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 80.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = "Referral code",
            style = TradeUpTypography.h2
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = "Enter your referral code",
            style = MaterialTheme.typography.body1
        )

        CommonEditText(
            error = isValidFilled,
            text = referralValue.value,
            placeHolderText = "Referral code (Optional)",
            focusedBorderColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = MaterialTheme.colors.primary,
            onValueChange = { referralValue.value = it }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
                .align(Alignment.End),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .defaultMinSize(minWidth = 100.dp),
                onClick = { navigateToProfileOnSkipScreen.invoke() },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(backgroundColor = TradeUpColors.greyButtonColor)
            ) {
                Text(
                    text = "Skip",
                    style = TradeUpTypography.h6
                )
            }
            Button(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .defaultMinSize(minWidth = 125.dp),
                onClick = {
                    keyboardController?.hideSoftwareKeyboard()
                    viewModel.doReferralApiCall(referralValue.value)
                },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Green,
                    disabledBackgroundColor = compositionLocalOf { Green }.current.copy(alpha = 0.08f)
                ),
                enabled = isValidate
            ) {
                Text(
                    text = "Continue",
                    style = TradeUpTypography.h6
                )
            }
        }
    }
}