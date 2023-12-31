package com.vismijatech.instantpost.screens.saved.whatsappsaves

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.ContentScreenTopAppBar
import com.vismijatech.instantpost.components.SavedContentCard
import com.vismijatech.instantpost.db.entities.WhatsappContent
import com.vismijatech.instantpost.utils.convertIntToDate

/**
 * Created by Vivek Sawant on 29-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedWhatsappScreen(navController: NavHostController) {
    val viewModel: SavedWhatsappViewModel = hiltViewModel()
    val contentState: State<List<WhatsappContent>> = viewModel.whatsappContent.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ContentScreenTopAppBar(
                title = stringResource(id = R.string.dashboardScreen_whatsapp_dashboardItem),
                navController = navController
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn{
                items(contentState.value){whatsappContent ->
                    SavedContentCard(
                        date = convertIntToDate(whatsappContent.date),
                        content = whatsappContent.content,
                        context = context
                    ){
                        viewModel.deleteContent(whatsappContent)
                    }
                }
            }
        }
    }
}