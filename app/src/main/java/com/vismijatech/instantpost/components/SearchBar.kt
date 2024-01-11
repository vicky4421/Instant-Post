package com.vismijatech.instantpost.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.vismijatech.instantpost.db.entities.QueryHistory
import com.vismijatech.instantpost.screens.images.pexel.PexelViewModel
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily

/**
 * Created by Vivek Sawant on 07-01-2024.
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    viewModel: PexelViewModel,
    query: String,
    valueState: MutableState<String>,
    queryList: List<QueryHistory>,
    deleteQuery: (QueryHistory) -> Unit,
    searchQuery: (QueryHistory) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    isSingleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Search,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    supportingText: @Composable() (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onImAction: () -> Unit,
) {
    val isActive by remember {
        derivedStateOf { valueState.value.isNotEmpty() }
    }
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
            valueState.value = ""
            keyboardController?.hide()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        singleLine = isSingleLine,
        trailingIcon = {
            IconButton(onClick = {
                viewModel.getSearchImageResponse(query = query)
                viewModel.insertQueryHistory(QueryHistory(query = query))
                valueState.value = ""
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        },
        isError = isError,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(size = 10.dp)
    )
    AnimatedVisibility(visible = isActive, modifier = Modifier.background(Color.Transparent)) {
        LazyColumn(
            modifier = Modifier.background(Color.Transparent).clip(shape = RoundedCornerShape(size = 10.dp)).zIndex(5f).fillMaxSize()
        ) {
            items(queryList) { queryHistory ->
                HistoryCard(
                    modifier = Modifier,
                    query = queryHistory.query,
                    onIconClick = { deleteQuery(queryHistory) }) {
                    searchQuery(queryHistory)
                    valueState.value = ""
                }
            }
        }
    }
}

@Composable
fun HistoryCard(query: String, modifier: Modifier = Modifier, onIconClick: () -> Unit, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .background(color = Color.Transparent)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { onClick() }
                .background(color = Color.Transparent)
        ) {
            Text(
                text = query,
                style = TextStyle(
                    fontFamily = PoppinsFontFamily,
                    fontSize = 18.sp,
                    background = Color.Transparent
                ),
                modifier = Modifier
                    .weight(5f)
                    .padding(start = 16.dp)
            )
            IconButton(
                onClick = { onIconClick() },
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color.Transparent)
            ) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
            }
        }
    }
}