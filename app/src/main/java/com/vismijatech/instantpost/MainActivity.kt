package com.vismijatech.instantpost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vismijatech.instantpost.navigation.InstantPostNavigation
import com.vismijatech.instantpost.ui.theme.InstantPostTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstantPostApp()
        }
    }
}

@Composable
fun InstantPostApp() {
    InstantPostTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            InstantPostNavigation()
        }
    }
}

@Preview
@Composable
fun InstantPostPreview() {
    InstantPostApp()
}