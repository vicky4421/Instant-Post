package com.vismijatech.instantpost.screens.saved.promotionalsaves

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.ContentScreenTopAppBar
import com.vismijatech.instantpost.screens.LoadingScreen
import com.vismijatech.instantpost.components.SavedContentCard
import com.vismijatech.instantpost.db.entities.PromotionalContent
import com.vismijatech.instantpost.screens.saved.NoDataScreen
import com.vismijatech.instantpost.utils.convertIntToDate

/**
 * Created by Vivek Sawant on 29-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedPromotionalScreen(navController: NavHostController) {
    val viewModel: SavedPromotionalViewModel = hiltViewModel()
    val contentState: State<List<PromotionalContent>> =
        viewModel.promotionalContent.collectAsState()
    val context = LocalContext.current
    val loading by viewModel.loading.collectAsState()
    val isPromotionalContentEmpty by viewModel.isEmpty.collectAsState()

    Scaffold(
        topBar = {
            ContentScreenTopAppBar(
                title = stringResource(id = R.string.whatsappScreen_promotional_whatsappItem),
                navController = navController
            )
        }
    ) {
        if (loading) LoadingScreen()
        else if (isPromotionalContentEmpty) NoDataScreen(context = context)
        else {
            Surface(
                modifier = Modifier.padding(it)
            ) {
                LazyColumn{
                    items(contentState.value){promotionalContent ->
                        SavedContentCard(
                            date = convertIntToDate(promotionalContent.date),
                            content = promotionalContent.content,
                            context = context
                        ){
                            viewModel.deleteContent(promotionalContent)
                        }
                    }
                }
            }
        }
    }
}