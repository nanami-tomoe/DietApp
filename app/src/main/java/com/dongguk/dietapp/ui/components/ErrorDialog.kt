package com.dongguk.dietapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dongguk.dietapp.ui.Nanum
import com.dongguk.dietapp.ui.NanumBold
import com.dongguk.dietapp.ui.theme.Orange01

@Composable
fun ErrorDialog(
//    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
//        title = {
//            Text(
//                text = title,
//                style = TextStyle(
//                    fontFamily = NanumBold,
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//            )
//        },
        text = {
            Text(
                text = message,
                style = TextStyle(
                    fontFamily = Nanum,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange01,
                    contentColor = Color.White
                )
            ) {
                Text(text = "확인")
            }
        }
    )
}
