package com.relayapp.live.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.relayapp.live.presentation.ui.theme.Green
import com.relayapp.live.presentation.ui.theme.TradeUpColors
import com.relayapp.live.presentation.ui.theme.navigationBackIconColor

@Composable
fun CommonEditText(
    modifier: Modifier = Modifier,
    text: String,
    placeHolderText: String,
    titleText: String? = null,
    onValueChange: (String) -> Unit = {},
    shape: Shape = RoundedCornerShape(8.dp),
    focusedBorderColor: Color = MaterialTheme.colors.primary,
    unfocusedBorderColor: Color = MaterialTheme.colors.primary,
    cursorColor: Color = Green,
    error: Boolean,
    leadingIcon: @Composable (() -> Unit)? = null,
    onclick: () -> Unit = {},
    enabled: Boolean = true,
    keyBoardType: KeyboardType = KeyboardType.Text
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = text)) }
    val textFieldValue = textFieldValueState.copy(text = text)
    val keyboardController = LocalTextInputService.current
    Column(
        modifier = modifier.padding(top = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        if (titleText != null) {
            Text(
                modifier = modifier.padding(start = 4.dp),
                text = titleText,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        }
        val customTextSelectionColors = TextSelectionColors(
            handleColor = cursorColor,
            backgroundColor = cursorColor.copy(alpha = 0.4f)
        )
        CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { onclick() },
                value = textFieldValue,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = TradeUpColors.navigationBackIconColor.copy(alpha = 0.05f),
                    focusedBorderColor = if (!error) Color.Red else focusedBorderColor,
                    unfocusedBorderColor = if (!error) Color.Red else unfocusedBorderColor,
                    cursorColor = cursorColor,
                    textColor = Color.Gray,
                ),
                onValueChange = {
                    textFieldValueState = it
                    if (text != it.text) {
                        onValueChange(it.text)
                    }
                },
                textStyle = MaterialTheme.typography.overline.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TradeUpColors.navigationBackIconColor,
                ),
                singleLine = true,
                shape = shape,
                placeholder = {
                    Text(
                        text = placeHolderText,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = TradeUpColors.navigationBackIconColor.copy(0.5f),
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyBoardType
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hideSoftwareKeyboard() }),
                leadingIcon = leadingIcon,
                enabled = enabled
            )
        }
    }
}