package com.dongguk.dietapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dongguk.dietapp.viewmodel.MealRecordViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: MealRecordViewModel = MealRecordViewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("start") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        composable("start") {
            StartRecordScreen(onStartClicked = {
                navController.navigate("meal_photo_type")
            })
        }

        composable("meal_photo_type") {
            MealPhotoAndTypeScreen(
                viewModel = viewModel,
                onNextClicked = {
                    navController.navigate("meal_details_input")
                }
            )
        }

        composable("meal_details_input") {
            MealDetailsInputScreen(
                viewModel = viewModel,
                onSubmitClicked = {
                    navController.navigate("diet_app_screen")
                }
            )
        }

        composable("diet_app_screen") {
            DietAppScreen()
        }
    }
}
