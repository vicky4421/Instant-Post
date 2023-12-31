package com.vismijatech.instantpost.screens.saved.businesssaves

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.vismijatech.instantpost.db.entities.BusinessContent
import com.vismijatech.instantpost.utils.convertIntToDate

/**
 * Created by Vivek Sawant on 30-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedBusinessScreen(navController: NavHostController) {
    val viewModel: SavedBusinessViewModel = hiltViewModel()
    val businessContent: State<List<BusinessContent>> = viewModel.businessContent.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ContentScreenTopAppBar(
                title = stringResource(id = R.string.whatsappScreen_business_whatsappItem),
                navController = navController
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn{
                items(businessContent.value){businessContent ->
                    SavedContentCard(
                        date = convertIntToDate(businessContent.date),
                        content = businessContent.content,
                        context = context
                    ){
                        viewModel.deleteContent(businessContent)
                    }
                }
            }
        }
    }
}