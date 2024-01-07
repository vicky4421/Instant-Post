package com.vismijatech.instantpost.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vismijatech.instantpost.R

/**
 * Created by Vivek Sawant on 07-01-2024.
 */

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    SearchBar(query = mutableStateOf(""), {})
}

@Composable
fun SearchBar(
    query: MutableState<String>,
    onSearchClick: () -> Unit
) {
    InputText(
        valueState = query,
        label = stringResource(id = R.string.imageScreen_search),
        trailingIcon = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        },
        modifier = Modifier.padding(0.dp)) {
        onSearchClick()
    }
}