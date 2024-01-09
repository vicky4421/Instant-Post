package com.vismijatech.instantpost.screens.images.pexel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.AppBar
import com.vismijatech.instantpost.components.BottomBar
import com.vismijatech.instantpost.components.SearchBar
import com.vismijatech.instantpost.db.entities.QueryHistory
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.screens.LoadingScreen

/**
 * Created by Vivek Sawant on 04-01-2024.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PexelScreen(navController: NavHostController) {
    val viewModel: PexelViewModel = hiltViewModel()
    val loading by viewModel.loading.collectAsState()
    val pexelResponse by viewModel.pexelResponse.collectAsState()
    val photoList = pexelResponse?.photos ?: listOf()
    val context = LocalContext.current
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val queryHistoryList by viewModel.queryHistory.collectAsState()
    Scaffold(
        topBar = {
            AppBar(
                onEndIconClick = { },
                startIcon = Icons.Default.Home,
                endIcon = null,
                title = stringResource(id = R.string.imageScreen_title)
            ) { navController.navigate(InstantPostScreen.DashboardScreen.name) }
        },
        bottomBar = { BottomBar(navController = navController, initialIndex = 1) }
    ) { it ->
        Surface(
            modifier = Modifier.padding(it)
        ) {
            if (loading) LoadingScreen() else {
                Column {
                    SearchBar(
                        viewModel = viewModel,
                        query = searchQuery.value,
                        valueState = searchQuery,
                        label = "Search",
                        deleteQuery = { queryHistory ->
                            viewModel.deleteQueryHistory(queryHistory)
                        },
                        queryList = queryHistoryList,
                        searchQuery = { history ->
                            viewModel.getSearchImageResponse(query = history.query)
                        }
                    ) {
                        viewModel.getSearchImageResponse(query = searchQuery.value)
                        viewModel.insertQueryHistory(QueryHistory(query = searchQuery.value))
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(count = 3), content = {
                            items(photoList) { photo ->
                                Card(
                                    modifier = Modifier
                                        .size(width = 100.dp, height = 100.dp)
                                        .padding(1.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surface
                                    ),
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(context)
                                            .data(data = photo.src.large)
                                            .crossfade(enable = true)
                                            .build(),
                                        contentDescription = photo.alt,
                                        contentScale = ContentScale.Crop,
                                        placeholder = painterResource(id = R.drawable.ic_image_filled),
                                        modifier = Modifier.clip(shape = RoundedCornerShape(size = 5.dp))
                                    )
                                }
                            }
                        },
                    )
                }
            }
        }
    }
}
