package com.dongguk.dietapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dongguk.dietapp.ui.theme.Ivory


@Composable
fun DietAppScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Ivory),
        Arrangement.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(26.dp, Alignment.Bottom),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "한달간 섭취한 칼로리 총량",
                style = TextStyle(
                    fontFamily = NotoSansBold,
                    fontSize = 24.sp
                )
            )
        }
    }
}