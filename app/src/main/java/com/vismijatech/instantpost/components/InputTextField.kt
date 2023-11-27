package com.vismijatech.instantpost.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.vismijatech.instantpost.R

/**
 * Created by Vivek Sawant on 27-11-2023.
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun InputText(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    textStyle: TextStyle = TextStyle(
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onBackground
    ),
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    onTrailingIconClick: () -> Unit = {},
    icon: Int = R.drawable.ic_next,
    trailingIcon: @Composable (() -> Unit)? = {
        IconButton(onClick = { onTrailingIconClick() }) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Next",
                modifier = Modifier.scale(2.5f)
            )
        }
    }
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        label = { Text(text = label) },
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = KeyboardActions {
            onImeAction()
            keyboardController?.hide()
        },
        trailingIcon = trailingIcon,
        singleLine = false
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun InputTextPreview() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val text by remember {
            mutableStateOf("")
        }
        InputText(
            value = text,
            onValueChanged = {},
            label = "name",
        )
    }
}

