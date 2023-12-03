package com.vismijatech.instantpost.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.navigation.InstantPostScreen
import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(navController: NavHostController) {

    Scaffold {
        BoxWithConstraints(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var state by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    while (true) {
                        val startDelay = Random.nextLong(300, 900)
                        delay(startDelay)
                        state = true
                        delay(3000)
                        state = false
                        delay(2000 - startDelay)
                    }
                }
                val animAlpha by animateFloatAsState(
                    targetValue = if (state) 1f else 0f,
                    animationSpec = tween(
                        durationMillis = 900,
                        easing = FastOutSlowInEasing
                    ), label = ""
                )
                val animRotation by animateFloatAsState(
                    targetValue = if (state) 360f else 0f,
                    animationSpec = tween(
                        durationMillis = 900,
                        easing = FastOutSlowInEasing
                    ), label = ""
                )
                val animScale by animateFloatAsState(
                    targetValue = if (state) 1f else 0f,
                    animationSpec = tween(
                        durationMillis = 900,
                        easing = FastOutSlowInEasing
                    ), label = ""
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(128.dp)
                        .graphicsLayer {
                            alpha = animAlpha
                            rotationX = animRotation
                            rotationY = animRotation
                            rotationZ = animRotation
                            scaleX = animScale
                            scaleY = animScale
                        }
                )
                AnimatedText(
                    text = "Instant Post",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                )
            }
        }
    }
    LaunchedEffect(key1 = Unit) {
        delay(timeMillis = 5000)
        navController.popBackStack()
        if (FirebaseAuth.getInstance().currentUser != null) navController.navigate(InstantPostScreen.DashboardScreen.name)
        else navController.navigate(InstantPostScreen.LoginScreen.name)

        /*
        TODO: check if user is removed from the firebase console auth and realtime database if the user is still able to
         navigate to dashboard, right now its yes, but dashboard is not implemented.
         After dashboard implementation before directly going to dashboard check another condition that user is exists
         in database.
         */

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}
