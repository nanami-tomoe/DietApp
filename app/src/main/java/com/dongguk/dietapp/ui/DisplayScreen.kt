package com.dongguk.dietapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.dongguk.dietapp.R
import com.dongguk.dietapp.data.RecordUiState
import com.dongguk.dietapp.viewmodel.MealRecordViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DisplayScreen(viewModel: MealRecordViewModel) {
    val meals = viewModel.meals

    // 현재 선택된 월 (기본값: 첫 번째 월)
    val groupedMeals = meals.groupBy { it.date.substring(0, 7) } // yyyy-MM으로 그룹화
    val months = groupedMeals.keys.sorted() // 정렬된 월 리스트
    val selectedMonth = remember { mutableStateOf(months.firstOrNull() ?: "") }

    // 상세 내용 표시를 위한 상태 관리
    var selectedMeal by remember { mutableStateOf<RecordUiState?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE9D0))
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
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Spacer(modifier = Modifier.height(25.dp))

            // 월별 LazyRow 추가
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(months) { month ->
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (month == selectedMonth.value) Color(0xFFFF9055) else Color(
                                    0xFFFFE9D0
                                ),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .shadow(
                                elevation = 5.dp,
                                spotColor = Color(0x1A000000),
                                ambientColor = Color(0x1A000000),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .border(
                                width = 3.dp,
                                color = Color(0xFFFFE9D0),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .width(85.dp)
                            .height(44.dp)
                            .clickable { selectedMonth.value = month },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = month,
                            color = if (month == selectedMonth.value) Color.White else Color.Gray,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = NanumBold,
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 선택된 월의 데이터 LazyColumn으로 표시
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, end = 30.dp),
                verticalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                items(groupedMeals[selectedMonth.value] ?: emptyList()) { meal ->
                    Card(
                        modifier = Modifier
                            .shadow(
                                elevation = 5.dp,
                                spotColor = Color(0x1A000000),
                                ambientColor = Color(0x1A000000)
                            )
                            .width(333.dp)
                            .height(130.dp)
                            .clickable {
                                selectedMeal = meal // 클릭된 데이터를 저장
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFC07F)
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // 음식 사진
                            AsyncImage(
                                model = meal.photoUri, // 이미지 URI
                                contentDescription = "Meal Photo",
                                modifier = Modifier
                                    .size(100.dp) // 사진 크기
                                    .clip(RoundedCornerShape(10.dp)) // 모서리를 둥글게 클리핑
                                    .background(
                                        Color.LightGray,
                                        shape = RoundedCornerShape(10.dp)
                                    ), // 배경색 설정
                                contentScale = ContentScale.Crop // 이미지 크기 조정
                            )

                            Spacer(modifier = Modifier.width(15.dp))

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                // 음식 이름
                                Text(
                                    text = "음식 이름: ${meal.selectedMeal}",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 30.sp,
                                        fontFamily = NanumBold,
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFFFFFFFF)
                                    )
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                // 날짜
                                Text(
                                    text = "식사 날짜: ${meal.date}",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 30.sp,
                                        fontFamily = NanumBold,
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFFFFFFFF)
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // 선택된 데이터가 있을 경우 상세 화면 표시
    selectedMeal?.let { meal ->
        MealDetailDialog(meal = meal) {
            selectedMeal = null // 다이얼로그 닫기
        }
    }
}

@Composable
fun MealDetailDialog(meal: RecordUiState, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "상세 정보",
                style = TextStyle(
                    fontFamily = NanumBold, // Nanum 폰트 설정
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // 텍스트 색상 설정
                )
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White) // 백그라운드 흰색
                    .padding(8.dp), // 여백 추가
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = meal.photoUri, // 이미지 URI
                    contentDescription = "Meal Photo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "음식: ${meal.selectedMeal}",
                    style = TextStyle(
                        fontFamily = Nanum,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )

                // 조식, 중식, 석식 이면 반찬도 표시. 선택한 반찬이 없으면 X 표시
                if (meal.selectedMealType in listOf("조식", "중식", "석식")) {
                    Text(
                        text = "반찬: ${meal.selectedSideDishes ?: "X"}",
                        style = TextStyle(
                            fontFamily = Nanum,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    )
                }

                Text(
                    text = "칼로리: ${meal.mealCalories} kcal",
                    style = TextStyle(
                        fontFamily = Nanum,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )
                Text(
                    text = "장소: ${meal.selectedMealLocation}",
                    style = TextStyle(
                        fontFamily = Nanum,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )
                Text(
                    text = "리뷰: ${meal.review}",
                    style = TextStyle(
                        fontFamily = Nanum,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )
                Text(
                    text = "날짜: ${meal.date}",
                    style = TextStyle(
                        fontFamily = Nanum,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier
                    .background(Color(0xFFFF8200), shape = RoundedCornerShape(8.dp)) // 버튼 배경색 설정
                    .padding(horizontal = 12.dp, vertical = 4.dp) // 내부 패딩 추가
            ) {
                Text(
                    text = "닫기",
                    style = TextStyle(
                        fontFamily = NanumBold, // Nanum 폰트 설정
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // 버튼 텍스트는 흰색
                    )
                )
            }
        },
        containerColor = Color.White // 다이얼로그 전체 배경 흰색
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DisplayScreenPreview() {
    val viewModel = MealRecordViewModel() // Dummy ViewModel for preview
    DisplayScreen(viewModel)
}
