package com.vismijatech.instantpost.screens.saved.personalsaves

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.components.ContentScreenTopAppBar
import com.vismijatech.instantpost.components.LoadingScreen
import com.vismijatech.instantpost.components.SavedContentCard
import com.vismijatech.instantpost.screens.saved.NoDataScreen
import com.vismijatech.instantpost.utils.convertIntToDate

/**
 * Created by Vivek Sawant on 30-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedPersonalScreen(navController: NavHostController) {
    val viewModel: SavedPersonalViewModel = hiltViewModel()
    val contentState = viewModel.personalContent.collectAsState()
    val context = LocalContext.current
    val loading by viewModel.loading.collectAsState()
    val isPersonalContentEmpty by viewModel.isEmpty.collectAsState()

    Scaffold(
        topBar = {
            ContentScreenTopAppBar(
                title = stringResource(id = R.string.whatsappScreen_personal_whatsappItem),
                navController = navController
            )
        }
    ) {
        if (loading) LoadingScreen()
        else if (isPersonalContentEmpty) NoDataScreen(context = context)
        else {
            Surface(
                modifier = Modifier.padding(it)
            ) {
                LazyColumn{
                    items(contentState.value){personalContent ->
                        SavedContentCard(
                            date = convertIntToDate(personalContent.date),
                            content = personalContent.content,
                            context = context
                        ){
                            viewModel.deleteContent(personalContent)
                        }
                    }
                }
            }
        }
    }
}