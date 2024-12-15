package com.dongguk.dietapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dongguk.dietapp.viewmodel.MealRecordViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
                    navController.navigate("meal_analysis_screen")
                }
            )
        }

        // 식사 분석 화면
        composable("meal_analysis_screen") {
            MealAnalysisScreen(
                viewModel = viewModel,
                navController = navController // NavigationController 전달
            )
        }

        // 식사 기록 화면
        composable("meal_record") {
            DisplayScreen(viewModel = viewModel) // MealRecordViewModel을 전달
        }
    }
}
