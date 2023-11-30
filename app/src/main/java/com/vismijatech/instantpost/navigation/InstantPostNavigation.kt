package com.vismijatech.instantpost.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vismijatech.instantpost.screens.blog.blog.BlogScreen
import com.vismijatech.instantpost.screens.blog.travel.TravelBlogScreen
import com.vismijatech.instantpost.screens.credits.CreditScreen
import com.vismijatech.instantpost.screens.dashboard.DashboardScreen
import com.vismijatech.instantpost.screens.login.LoginScreen
import com.vismijatech.instantpost.screens.mail.business.BusinessMailScreen
import com.vismijatech.instantpost.screens.mail.newsletter.NewsletterScreen
import com.vismijatech.instantpost.screens.mail.personal.PersonalMailScreen
import com.vismijatech.instantpost.screens.mail.promotional.PromotionalMailScreen
import com.vismijatech.instantpost.screens.signup.SignupScreen
import com.vismijatech.instantpost.screens.socialmedia.SocialMediaCaptionScreen
import com.vismijatech.instantpost.screens.splash.SplashScreen
import com.vismijatech.instantpost.screens.whatsapp.WhatsappScreen

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@Composable
fun InstantPostNavigation(){

    // define NavHostController
    val navController: NavHostController = rememberNavController()

    // define NavHost
    NavHost(navController = navController, startDestination = InstantPostScreen.SplashScreen.name){

        // define composable here
        composable(route = InstantPostScreen.SplashScreen.name){
            SplashScreen(navController = navController)
            navController.popBackStack()
        }

        composable(route = InstantPostScreen.LoginScreen.name){
            LoginScreen(navController = navController)
        }

        composable(route = InstantPostScreen.SignupScreen.name){
            SignupScreen(navController = navController)
        }

        composable(route = InstantPostScreen.DashboardScreen.name){
            DashboardScreen(navController = navController)
        }

        composable(route = InstantPostScreen.SocialMediaCaptionScreen.name){
            SocialMediaCaptionScreen(navController = navController)
        }

        composable(route = InstantPostScreen.WhatsappScreen.name){
            WhatsappScreen(navController = navController)
        }

        composable(route = InstantPostScreen.BlogScreen.name){
            BlogScreen(navController = navController)
        }

        composable(route = InstantPostScreen.TravelBlogScreen.name){
            TravelBlogScreen(navController = navController)
        }

        composable(route = InstantPostScreen.BusinessMailScreen.name){
            BusinessMailScreen(navController = navController)
        }

        composable(route = InstantPostScreen.NewsletterScreen.name){
            NewsletterScreen(navController = navController)
        }

        composable(route = InstantPostScreen.PersonalMailScreen.name){
            PersonalMailScreen(navController = navController)
        }

        composable(route = InstantPostScreen.PromotionalMailScreen.name){
            PromotionalMailScreen(navController = navController)
        }

        composable(route = InstantPostScreen.CreditScreen.name){
            CreditScreen(navController = navController)
        }

    }

}