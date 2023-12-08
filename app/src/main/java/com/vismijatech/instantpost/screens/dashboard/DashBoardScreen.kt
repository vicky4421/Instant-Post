package com.vismijatech.instantpost.screens.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.vismijatech.instantpost.components.AppBar
import com.vismijatech.instantpost.components.LoadingScreen
import com.vismijatech.instantpost.navigation.InstantPostScreen
import kotlinx.coroutines.launch

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController, viewModel: DashboardViewModel = hiltViewModel()) {
    val auth = FirebaseAuth.getInstance()
    val uid = auth.currentUser?.uid.toString()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(uid){
        viewModel.getUserData(uid)
    }

    val userData by viewModel.userData.collectAsStateWithLifecycle()
    val loading by viewModel.isLoading.collectAsState()

    if (loading) LoadingScreen() else userData?.let { user ->
        NavigationDrawer(
            context = context,
            image = user.imageUrl.orEmpty(),
            name = user.username.orEmpty(),
            credits = user.credits.toString()
        ){drawerState ->
            Scaffold(
                topBar = {
                    AppBar(onEndIconClick = {
                        auth.signOut()
                        navController.navigate(InstantPostScreen.LoginScreen.name)
                    }) {
                        scope.launch { drawerState.open() }
                    }
                }
            ) {
                Surface(
                    modifier = Modifier
                        .padding(it)
                        .verticalScroll(rememberScrollState())
                ) {

                }
            }
        }
    }

}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(navController = rememberNavController())
}