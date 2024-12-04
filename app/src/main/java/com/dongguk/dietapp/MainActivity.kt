//package com.dongguk.dietapp
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import com.dongguk.dietapp.ui.MealInputScreen
//import com.dongguk.dietapp.ui.theme.DietAppTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            DietAppTheme {
//                MealInputScreen()
//            }
//        }
//    }
//}
//
////@Composable
////fun MainApp() {
////    var showSplash by remember { mutableStateOf(true) }
////
////    if (showSplash) {
////        SplashScreen {
////            showSplash = false
////        }
////    } else {
////        StartRecordScreen(onStartClicked = { /*TODO*/ })
////    }
////}