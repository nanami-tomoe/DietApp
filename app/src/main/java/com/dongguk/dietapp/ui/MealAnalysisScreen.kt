package com.dongguk.dietapp.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dongguk.dietapp.R
import com.dongguk.dietapp.ui.components.BottomNavigationBar
import com.dongguk.dietapp.ui.theme.Orange01
import com.dongguk.dietapp.viewmodel.MealRecordViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MealAnalysisScreen(
    viewModel: MealRecordViewModel,
    navController: NavController,
) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 23.dp, top = 48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_orange),
                contentDescription = "orange logo",
                modifier = Modifier
                    .size(54.dp) // 크기 명시
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "동국한끼",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontFamily = Jalnan,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF9A541A),
                )
            )
        }

        Spacer(modifier = Modifier.height(25.29.dp))

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MonthlyCalorieView(viewModel = viewModel)

            Spacer(modifier = Modifier.height(12.dp))

            MonthlyMealCostView(viewModel = viewModel)

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                MealInputButton(
                    text = "식사 입력 하기",
                    iconRes = R.drawable.ic_plus,
                    onClick = {
                        navController.navigate("meal_photo_type")
                    }
                )

                Spacer(modifier = Modifier.width(12.dp))

                MealInputButton(
                    text = "식사 기록 보기",
                    iconRes = R.drawable.ic_magnifier,
                    onClick = {
                        navController.navigate("meal_record")
                    }
                )
            }

        }
    }
}

@Composable
fun MealInputButton(
    text: String, // 버튼 내부 텍스트
    iconRes: Int, // 아이콘 리소스 ID
    onClick: () -> Unit // 클릭 이벤트
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            // 그림자 추가
            .border(
                width = 1.dp,
                color = Color(0xFFFFC07F),
                shape = RoundedCornerShape(20.dp)
            ) // 테두리 추가
            .background(
                color = Color(0xFFFFC07F),
                shape = RoundedCornerShape(20.dp)
            ) // 배경색과 모서리 둥글기 설정
            .width(178.dp)
            .height(87.dp)
            .clickable(onClick = onClick), // 클릭 이벤트 처리
    ) {
        // 내부 콘텐츠
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // 아이콘
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .size(29.dp) // 아이콘 크기
                    .align(Alignment.TopStart) // 상단 왼쪽 정렬
                    .padding(start = 15.dp, top = 15.dp) // 왼쪽, 위쪽 패딩
            )
            // 텍스트
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.align(Alignment.Center) // 텍스트를 중앙에 정렬
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthlyCalorieView(
    viewModel: MealRecordViewModel,
) {
    val totalCalorie = viewModel.calculateTotalCaloriesForLastMonth()

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .shadow(
                elevation = 5.dp,
                spotColor = Color(0x1A000000),
                ambientColor = Color(0x1A000000)
            )
    ) {
        Box(
            modifier = Modifier
                .width(368.dp)
                .height(119.dp)
                .background(color = Color(0xFFFFE9D0), shape = RoundedCornerShape(size = 20.dp))
                .border(
                    width = 1.dp,
                    color = Color(0xFFFFE9D0),
                    shape = RoundedCornerShape(size = 20.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(17.dp))

                // 상단 텍스트
                Text(
                    text = "최근 1달 간 섭취한 칼로리",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontFamily = Nanum,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    ),
                )

                Spacer(modifier = Modifier.height(19.dp))

                Row(
                    modifier = Modifier
                        .padding(0.dp)
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    // 칼로리 아이콘
                    Image(
                        painter = painterResource(id = R.drawable.ic_kcal),
                        contentDescription = "calorie",
                        modifier = Modifier.size(40.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // 칼로리 텍스트
                    Text(
                        text = "${String.format("%,d", totalCalorie)}kcal",
                        style = TextStyle(
                            fontSize = 28.sp,
                            lineHeight = 29.sp,
                            fontFamily = Nanum,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000)
                        )
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthlyMealCostView(
    viewModel: MealRecordViewModel,
) {
    // 선택된 식사 종류를 상태로 관리
    var selectedMealType by remember { mutableStateOf("조식") } // 기본값: 조식

    // 최근 한달 식사 비용 데이터 가져오기
    val mealTypeData = viewModel.calculateMealTypeCostAndPercentageForLastMonth()

    // 최근 한달 총 비용 계산
    val totalMonthlyCost = mealTypeData.values.sumOf { it.first }

    // 현재 선택된 식사 종류의 데이터
    val selectedMealData = mealTypeData[selectedMealType] ?: Pair(0, 0f)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000))
    ) {
        Box(
            modifier = Modifier
                .width(368.dp)
                .height(276.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 20.dp))
                .border(
                    width = 1.dp,
                    color = Color(0xFFFFE9D0),
                    shape = RoundedCornerShape(size = 20.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween, // 요소 간 균등한 간격 배치
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(17.dp))

                // 상단 텍스트
                Text(
                    text = "최근 1달 간 지불한 비용",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 29.sp,
                        fontFamily = Nanum,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 반원형 차트
                Box(
                    modifier = Modifier
                        .size(224.dp) // 차트 크기 명확히 지정
                ) {
                    SemiCircularChart(
                        percentage = selectedMealData.second / 100, // 비율을 소수로 변환
                        totalCost = totalMonthlyCost, // 총 비용 입력
                        mealCost = selectedMealData.first, // 선택된 식사 비용 입력
                        color = Color(0xFFFF8200),
                        backgroundColor = Color(0xFFFFE9D0)
                    )
                }
            }

            // 식사 종류 선택 버튼
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 209.dp, bottom = 29.dp)
            ) {
                MealTypeSelectButton(
                    mealTypes = listOf("조식", "중식", "석식", "간식", "음료"),
                    selectedMealType = selectedMealType,
                    onMealTypeSelected = { selectedMealType = it }
                )
            }
        }
    }
}

@Composable
fun MealTypeSelectButton(
    mealTypes: List<String>,
    selectedMealType: String,
    onMealTypeSelected: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth(),
    ) {
        mealTypes.forEach { mealType ->
            Button(
                onClick = { onMealTypeSelected(mealType) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = if (mealType == selectedMealType) Orange01 else Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(0.5.dp)
                    .width(60.dp)
                    .height(40.dp)
            ) {
                Text(
                    text = mealType,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontFamily = if (mealType == selectedMealType) NanumBold else Nanum,
                        color = if (mealType == selectedMealType) Orange01 else Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun SemiCircularChart(
    percentage: Float,
    totalCost: Int, // 한달간 총 비용
    mealCost: Int, // 선택된 식사 비용
    color: Color = Color(0xFFFF8200), // 주황색
    backgroundColor: Color = Color(0xFFFFE9D0) // 연한 주황색
) {
    Box(
        modifier = Modifier
            .size(224.dp)
            .background(Color.Transparent)
    ) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {
            // 배경 반원
            drawArc(
                color = backgroundColor,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                size = size,
                style = Stroke(width = 80f, cap = StrokeCap.Round),
            )
            // 현재 퍼센트 반원
            drawArc(
                color = color,
                startAngle = 180f,
                sweepAngle = 180f * percentage,
                useCenter = false,
                size = size,
                style = Stroke(width = 80f, cap = StrokeCap.Round)
            )
        }

        // 중앙 텍스트 또는 아이콘
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-20).dp), // 전체를 위로 살짝 이동
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // 요소들이 Column의 중심에 배치되도록 설정
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_won),
                contentDescription = "won",
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "${String.format("%,d", mealCost)}원",
                style = TextStyle(
                    fontSize = 28.sp,
                    lineHeight = 29.sp,
                    fontFamily = Nanum,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),

                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "${String.format("%,d", totalCost)}원 중",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 29.sp,
                    fontFamily = Nanum,
                    fontWeight = FontWeight(400),
                    color = Color(0x33000000),

                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun MealAnalysisScreenPreview() {
//    MonthlyMealCostView(viewModel = MealRecordViewModel())
//    MealAnalysisScreen(MealRecordViewModel())
//    MonthlyCalorieView()
//    SemiCircularChart(0.7f)
//    MealInputButton(text = "식사 추가",
//        iconRes = R.drawable.ic_plus, // 아이콘 리소스
//        onClick = {
//            // 클릭 이벤트 처리
//        })
}