package com.vismijatech.instantpost.screens.images.pexel

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.screens.LoadingScreen

/**
 * Created by Vivek Sawant on 04-01-2024.
 */
@Composable
fun PexelScreen() {
    val viewModel: PexelViewModel = hiltViewModel()
    val loading by viewModel.loading.collectAsState()
    val pexelResponse by viewModel.pexelResponse.collectAsState()
    val photoList = pexelResponse?.photos ?: listOf()
    val context = LocalContext.current

    Surface(
        modifier = Modifier.padding(16.dp)
    ) {
        if (loading) LoadingScreen() else {
            LazyVerticalGrid(columns = GridCells.Fixed(count = 3), content = {
                items(photoList) { photo ->
                    Card {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(data = photo.url)
                                .crossfade(enable = true)
                                .build(),
                            contentDescription = photo.alt,
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = R.drawable.ic_person),
                            modifier = Modifier.clip(shape = RoundedCornerShape(size = 5.dp))
                        )
                    }
                }
            })
        }
    }
}
