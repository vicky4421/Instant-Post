package com.vismijatech.instantpost.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily

/**
 * Created by Vivek Sawant on 27-11-2023.
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun InputText(
    valueState: MutableState<String>,
    modifier: Modifier = Modifier,
    label: String,
    isSingleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable () -> Unit,
    isError: Boolean = false,
    supportingText:  @Composable() (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onImAction: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
        },
        modifier = modifier
            .padding(bottom = 5.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        label = { Text(text = label, style = TextStyle(fontFamily = PoppinsFontFamily)) },
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = PoppinsFontFamily
        ),
        keyboardActions = KeyboardActions {
            onImAction()
            keyboardController?.hide()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        singleLine = isSingleLine,
        trailingIcon = trailingIcon,
        isError = isError,
        supportingText = supportingText,
        shape = RoundedCornerShape(size = 10.dp)
    )
}


