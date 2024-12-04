package com.dongguk.dietapp.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dongguk.dietapp.ui.theme.Ivory
import com.dongguk.dietapp.viewmodel.MealRecordViewModel


@Composable
fun DietAppScreen(
    viewModel: MealRecordViewModel,
    onNavigateToMealInput: () -> Unit
) {
    // 로그로 meals 상태 확인
    Log.d("DietAppScreen", "Meals: ${viewModel.meals}")

    // 데이터 소스에서 칼로리와 비용 계산
    val totalCalories = viewModel.meals.sumOf { it.mealCalories + (it.sideDishCalories ?: 0) } // 섭취 칼로리 총량
    val totalCost = viewModel.meals.sumOf { it.price } // 지불한 금액 총량

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Ivory),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        // 칼로리 총량
        Column(
            verticalArrangement = Arrangement.spacedBy(26.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "한달간 섭취한 칼로리 총량",
                style = TextStyle(
                    fontFamily = NotoSansBold,
                    fontSize = 24.sp
                )
            )
            Text(
                text = "$totalCalories kcal",
                style = TextStyle(
                    fontFamily = NotoSansBold,
                    fontSize = 20.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        // 식사 비용 총량
        Column(
            verticalArrangement = Arrangement.spacedBy(26.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "한달간 지불한 식사 비용",
                style = TextStyle(
                    fontFamily = NotoSansBold,
                    fontSize = 24.sp
                )
            )
            Text(
                text = "${totalCost}원",
                style = TextStyle(
                    fontFamily = NotoSansBold,
                    fontSize = 20.sp
                )
            )
            Button(onClick = { onNavigateToMealInput() }) {
                Text(text = "새 식사 입력")
            }
        }
    }
}

@Composable
@Preview
fun DietAppScreenPreview() {
    DietAppScreen(viewModel = MealRecordViewModel(), onNavigateToMealInput = {})
}