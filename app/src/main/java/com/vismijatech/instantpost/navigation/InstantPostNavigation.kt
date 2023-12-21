package com.vismijatech.instantpost.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vismijatech.instantpost.screens.SharedViewModel
import com.vismijatech.instantpost.screens.blog.BlogScreen
import com.vismijatech.instantpost.screens.blog.blog.OtherBlogScreen
import com.vismijatech.instantpost.screens.blog.travel.TravelBlogScreen
import com.vismijatech.instantpost.screens.credits.CreditScreen
import com.vismijatech.instantpost.screens.dashboard.DashboardScreen
import com.vismijatech.instantpost.screens.images.ImageScreen
import com.vismijatech.instantpost.screens.login.LoginScreen
import com.vismijatech.instantpost.screens.mail.EmailScreen
import com.vismijatech.instantpost.screens.mail.business.BusinessMailScreen
import com.vismijatech.instantpost.screens.mail.newsletter.NewsletterScreen
import com.vismijatech.instantpost.screens.mail.personal.PersonalMailScreen
import com.vismijatech.instantpost.screens.mail.promotional.PromotionalMailScreen
import com.vismijatech.instantpost.screens.result.ResultScreen
import com.vismijatech.instantpost.screens.signup.SignupScreen
import com.vismijatech.instantpost.screens.socialmedia.SocialMediaCaptionScreen
import com.vismijatech.instantpost.screens.splash.SplashScreen
import com.vismijatech.instantpost.screens.whatsapp.WhatsappScreen
import com.vismijatech.instantpost.screens.whatsapp.business.WhatsappBusinessScreen
import com.vismijatech.instantpost.screens.whatsapp.caption.WhatsappCaptionScreen
import com.vismijatech.instantpost.screens.whatsapp.newsletter.WhatsappNewsletterScreen
import com.vismijatech.instantpost.screens.whatsapp.personal.WhatsappPersonalScreen
import com.vismijatech.instantpost.screens.whatsapp.promotional.WhatsappPromotionalScreen
import com.vismijatech.instantpost.screens.whatsapp.reply.WhatsappReplyScreen

/**
 * Created by Vivek Sawant on 26-11-2023.
 */

@Composable
fun InstantPostNavigation() {

    // define NavHostController
    val navController: NavHostController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()

    // define NavHost
    NavHost(navController = navController, startDestination = InstantPostScreen.SplashScreen.name) {

        // define composable here
        composable(route = InstantPostScreen.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(route = InstantPostScreen.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(route = InstantPostScreen.SignupScreen.name) {
            SignupScreen(navController = navController)
        }

        composable(route = InstantPostScreen.DashboardScreen.name) {
            DashboardScreen(navController = navController)
        }
        
        composable(route = InstantPostScreen.SocialMediaCaptionScreen.name){
            SocialMediaCaptionScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = InstantPostScreen.WhatsappScreen.name) {
            WhatsappScreen(navController = navController)
        }

        composable(route = InstantPostScreen.OtherBlogScreen.name) {
            OtherBlogScreen(navController = navController)
        }

        composable(route = InstantPostScreen.TravelBlogScreen.name) {
            TravelBlogScreen(navController = navController)
        }

        composable(route = InstantPostScreen.BusinessMailScreen.name) {
            BusinessMailScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = InstantPostScreen.NewsletterScreen.name) {
            NewsletterScreen(navController = navController)
        }

        composable(route = InstantPostScreen.PersonalMailScreen.name) {
            PersonalMailScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = InstantPostScreen.PromotionalMailScreen.name) {
            PromotionalMailScreen(navController = navController)
        }

        composable(route = InstantPostScreen.CreditScreen.name) {
            CreditScreen(navController = navController)
        }

        composable(route = InstantPostScreen.EmailScreen.name) {
            EmailScreen(navController = navController)
        }

        composable(route = InstantPostScreen.BlogScreen.name) {
            BlogScreen(navController = navController)
        }

        composable(route = InstantPostScreen.ImageScreen.name) {
            ImageScreen(navController = navController)
        }

        composable(route = InstantPostScreen.WhatsappBusinessScreen.name) {
            WhatsappBusinessScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = InstantPostScreen.WhatsappCaptionScreen.name) {
            WhatsappCaptionScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = InstantPostScreen.WhatsappNewsletterScreen.name) {
            WhatsappNewsletterScreen(navController = navController)
        }

        composable(route = InstantPostScreen.WhatsappPersonalScreen.name) {
            WhatsappPersonalScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = InstantPostScreen.WhatsappPromotionalScreen.name) {
            WhatsappPromotionalScreen(navController = navController)
        }

        composable(route = InstantPostScreen.WhatsappReplyScreen.name) {
            WhatsappReplyScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = InstantPostScreen.ResultScreen.name + "/{query}"){
            val query = it.arguments?.getString("query")
            ResultScreen(navController = navController, query = query, sharedViewModel = sharedViewModel)
        }
    }

}