package com.vismijatech.instantpost

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vismijatech.instantpost.data.NAVIGATE_TO_DASHBOARD
import com.vismijatech.instantpost.navigation.InstantPostNavigation
import com.vismijatech.instantpost.navigation.InstantPostScreen
import com.vismijatech.instantpost.ui.theme.InstantPostTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            InstantPostApp(navController)
            if (intent.getBooleanExtra(NAVIGATE_TO_DASHBOARD, false)){
                navController.navigate(InstantPostScreen.DashboardScreen.name)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InstantPostApp(navController: NavHostController) {
    InstantPostTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            InstantPostNavigation(navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun InstantPostPreview() {
    InstantPostApp(rememberNavController())
}