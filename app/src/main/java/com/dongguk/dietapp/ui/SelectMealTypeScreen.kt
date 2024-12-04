package com.dongguk.dietapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dongguk.dietapp.R
import com.dongguk.dietapp.data.DataSource
import com.dongguk.dietapp.ui.theme.Ivory
import com.dongguk.dietapp.ui.theme.Orange01
import com.dongguk.dietapp.viewmodel.MealInputViewModel
import com.dongguk.dietapp.viewmodel.MealRecordViewModel
import com.dongguk.dietapp.viewmodel.SelectMealTypeViewModel

@Composable
fun SelectMealTypeScreen(viewModel: MealRecordViewModel = MealRecordViewModel()) {

    val uiState = viewModel.uiState
    val mealTypes = DataSource.mealTypes

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp)
            .background(Ivory),
        verticalArrangement = Arrangement.SpaceBetween // 위아래 간격을 자동 조정
    ) {
        // 상단 콘텐츠
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "식사 입력하기",
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 22.5.sp,
                    fontFamily = FontFamily(Font(R.font.notosansbold)),
                    fontWeight = FontWeight(700),
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(top = 36.dp, bottom = 24.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.rectangle5754),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(bottom = 16.dp)
            )

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // 배경색 투명
                    contentColor = Orange01 // 텍스트 색상
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp), // 버튼 그림자 제거
                contentPadding = PaddingValues(0.dp), // 버튼 내부 여백 제거
                modifier = Modifier
                    .height(15.dp)
            ) {
                Text(
                    text = "음식 사진을 선택하세요.",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 15.sp,
                        fontFamily = FontFamily(Font(R.font.notosans)),
                        fontWeight = FontWeight(500),
                        color = Orange01
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "식사 종류 선택",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.notosans)),
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // 식사 종류 버튼
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                mealTypes.forEach { meal ->
                    Button(
                        onClick = { viewModel.selectMealType(meal) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = if (uiState.selectedMealType == meal) Orange01 else Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(0.5.dp)
                            .width(60.dp)
                            .height(40.dp)
                    ) {
                        Text(
                            text = meal,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.notosans)),
                                fontWeight = FontWeight(400),
                                color = if (uiState.selectedMealType == meal) Orange01 else Color.Black,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }

        // Next 버튼
        Button(
            modifier = Modifier
                .width(358.dp) // 버튼 너비
                .height(56.dp)
                .align(Alignment.CenterHorizontally), // 버튼 높이
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange01,
                contentColor = Color.White // 텍스트 색상
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Next",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.notosansbold)), // 커스텀 폰트 적용
                    fontSize = 16.sp // 글자 크기
                )
            )
        }
    }
}



@Preview
@Composable
fun SelectMealTypePreview() {
    SelectMealTypeScreen(viewModel = MealRecordViewModel())
}