package com.vismijatech.instantpost.screens.saved.newslettersaves

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
import com.vismijatech.instantpost.components.LoadingScreen
import com.vismijatech.instantpost.components.SavedContentCard
import com.vismijatech.instantpost.db.entities.NewsletterContent
import com.vismijatech.instantpost.screens.saved.NoDataScreen
import com.vismijatech.instantpost.utils.convertIntToDate

/**
 * Created by Vivek Sawant on 30-12-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedNewsletterScreen(navController: NavHostController) {
    val viewModel: SavedNewsletterViewModel = hiltViewModel()
    val contentState: State<List<NewsletterContent>> = viewModel.newsletterContent.collectAsState()
    val context = LocalContext.current
    val loading by viewModel.loading.collectAsState()
    val isNewsletterContentEmpty by viewModel.isEmpty.collectAsState()

    Scaffold(
        topBar = {
            ContentScreenTopAppBar(
                title = stringResource(id = R.string.whatsappScreen_newsletter_whatsappItem),
                navController = navController
            )
        }
    ) {
        if (loading) LoadingScreen()
        else if (isNewsletterContentEmpty) NoDataScreen(context = context)
        else {
            Surface(
                modifier = Modifier.padding(it)
            ) {
                LazyColumn{
                    items(contentState.value){newsletterContent ->
                        SavedContentCard(
                            date = convertIntToDate(newsletterContent.date),
                            content = newsletterContent.content,
                            context = context
                        ){
                            viewModel.deleteContent(newsletterContent)
                        }
                    }
                }
            }
        }
    }
}