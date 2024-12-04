package com.dongguk.dietapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dongguk.dietapp.ui.AppNavigation
import com.dongguk.dietapp.ui.theme.DietAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DietAppTheme {
                AppNavigation()
            }
        }
    }
}