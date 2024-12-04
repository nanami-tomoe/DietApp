//package com.dongguk.dietapp.ui
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentWidth
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.Icon
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.dongguk.dietapp.R
//import com.dongguk.dietapp.ui.theme.Black
//import com.dongguk.dietapp.ui.theme.Ivory
//import com.dongguk.dietapp.ui.theme.Orange01
//import com.dongguk.dietapp.viewmodel.MealInputViewModel
//
//@Composable
//fun MealInputScreen(viewModel: MealInputViewModel = MealInputViewModel()) {
//    val sideDishes = remember { mutableStateListOf<String>() } // 동적으로 반찬 필드를 관리
//
//    val scrollState = rememberScrollState() // 스크롤 상태
//
////    val selectedLocation = viewModel.location
////    val locationOptions = when (selectedMealType) {
////        "조식", "중식", "석식" -> listOf("식당 1", "식당 2", "식당 3")
////        "간식", "음료" -> listOf("카페 1", "카페 2", "카페 3")
////        else -> emptyList()
////    }
////    var expanded by remember { mutableStateOf(false) } // 메뉴 확장 상태
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Ivory)
//            .verticalScroll(scrollState)
//    ) {
//        // 식사 종류 선택 버튼
//        Text(
//            text = "식사 입력하기",
//            style = TextStyle(
//                fontSize = 18.sp,
//                lineHeight = 22.5.sp,
//                fontFamily = FontFamily(Font(R.font.notosansbold)),
//                fontWeight = FontWeight(700),
//                color = Color(0xFF000000),
//            ),
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(top = 60.dp, bottom = 24.dp)
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        Text(
//            text = "식사 장소 선택",
//            style = TextStyle(
//                fontSize = 16.sp,
//                lineHeight = 20.sp,
//                fontFamily = FontFamily(Font(R.font.notosans)),
//                fontWeight = FontWeight(500),
//                color = Black,
//                textAlign = TextAlign.Center,
//            ),
//            modifier = Modifier
//                .padding(start = 16.dp, bottom = 8.dp)
//        )
//
//        DropdownWithSelectedText(viewModel)
//
//        Spacer(modifier = Modifier.height(25.dp))
//
//        ConditionalInputFields(viewModel, selectedMealType)
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        // 동적으로 추가된 반찬 입력 필드
//        sideDishes.forEachIndexed { index, sideDish ->
//            InputFieldWithLabel(
//                label = "반찬 ${index + 1} 입력",
//                text = sideDish,
//                onTextChange = { updatedText -> sideDishes[index] = updatedText },
//                placeholder = "반찬 이름"
//            )
//
//            Spacer(modifier = Modifier.height(10.dp))
//        }
//
//        Button(
//            modifier = Modifier
//                .padding(end = 17.dp)
//                .wrapContentWidth(), // 버튼 크기를 텍스트에 맞춤
//            onClick = { sideDishes.add("") }, // 클릭 시 빈 입력 필드 추가
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color.Transparent,
//                contentColor = Color.Gray
//            ),
//            shape = RoundedCornerShape(8.dp)
//        ) {
//            Text(
//                text = "반찬 추가",
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    lineHeight = 20.sp,
//                    fontFamily = FontFamily(Font(R.font.notosans)),
//                    fontWeight = FontWeight(400),
//                    color = Color.Gray,
//                    textAlign = TextAlign.Center,
//                )
//            )
//        }
//
//        Spacer(modifier = Modifier.height(25.dp))
//
//        // Next 버튼
//        Button(
//            modifier = Modifier
//                .width(358.dp) // 버튼 너비
//                .height(56.dp) // 버튼 높이
//                .align(Alignment.CenterHorizontally),
//            onClick = { /*TODO*/ },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Orange01,
//                contentColor = Color.White // 텍스트 색상
//            ),
//            shape = RoundedCornerShape(8.dp)
//        ) {
//            Text(
//                text = "Next",
//                style = TextStyle(
//                    fontFamily = FontFamily(Font(R.font.notosansbold)), // 커스텀 폰트 적용
//                    fontSize = 16.sp // 글자 크기
//                )
//            )
//        }
//    }
//
//    Spacer(modifier = Modifier.height(78.dp))
//}
//
//@Composable
//fun DropdownWithSelectedText(viewModel: MealInputViewModel) {
//    var expanded by remember { mutableStateOf(false) } // 드롭다운 메뉴 확장 상태
//    var selectedLocation by remember { mutableStateOf("") } // 선택된 텍스트 상태
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp) // 양옆 여백
//            .clip(RoundedCornerShape(8.dp)) // 둥근 모서리
//            .height(58.dp) // 고정 높이
//            .border(
//                width = 1.dp,
//                shape = RoundedCornerShape(8.dp),
//                color = Color.Gray
//            )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(start = 16.dp, end = 16.dp), // 텍스트와 아이콘 사이 여백
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            // 선택된 텍스트
//            Text(
//                text = if (selectedLocation.isEmpty()) "식사 장소" else selectedLocation,
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontFamily = FontFamily(Font(R.font.notosans)),
//                    fontWeight = FontWeight.Normal,
//                    color = if (selectedLocation.isEmpty()) Color.Gray else Color.Black
//                )
//            )
//
//            // 아래 방향 아이콘
//            Icon(
//                painter = painterResource(id = R.drawable.ic_arrow_down), // 아이콘 리소스
//                contentDescription = "Dropdown Arrow",
//                tint = Color.Gray,
//                modifier = Modifier
//                    .size(13.dp)
//                    .clickable { expanded = !expanded } // 클릭 이벤트
//            )
//        }
//
//        // DropdownMenu
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            // 드롭다운 메뉴 아이템 생성
//            val locationOptions = listOf("식당 1", "식당 2", "식당 3") // 선택 가능한 옵션들
//            locationOptions.forEach { location ->
//                DropdownMenuItem(
//                    text = { Text(text = location) },
//                    onClick = {
//                        selectedLocation = location // 선택한 텍스트 업데이트
//                        expanded = false // 드롭다운 닫기
//                    }
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun ConditionalInputFields(viewModel: MealInputViewModel, selectedMealType: String) {
//    when (selectedMealType) {
//        "조식", "중식", "석식" -> {
//            InputFieldWithLabel(
//                label = "메뉴 이름 입력",
//                text = viewModel.menuName,
//                onTextChange = { viewModel.menuName = it },
//                placeholder = "메뉴 이름"
//            )
//
//            Spacer(modifier = Modifier.height(25.dp))
//
//            InputFieldWithLabel(
//                label = "반찬 이름 입력",
//                text = viewModel.sideDish,
//                onTextChange = { viewModel.sideDish = it },
//                placeholder = "반찬 이름"
//            )
//        }
//        "간식" -> {
//            InputFieldWithLabel(
//                label = "간식 이름 입력",
//                text = viewModel.snackName,
//                onTextChange = { viewModel.snackName = it },
//                placeholder = "간식 이름"
//            )
//        }
//        "음료" -> {
//            InputFieldWithLabel(
//                label = "음료 이름 입력",
//                text = viewModel.drinkName,
//                onTextChange = { viewModel.drinkName = it },
//                placeholder = "음료 이름"
//            )
//        }
//    }
//}
//
//@Composable
//fun InputFieldWithLabel(
//    label: String,
//    text: String,
//    onTextChange: (String) -> Unit,
//    placeholder: String
//) {
//    Text(
//        text = label,
//        style = TextStyle(
//            fontSize = 16.sp,
//            lineHeight = 20.sp,
//            fontFamily = FontFamily(Font(R.font.notosans)),
//            fontWeight = FontWeight(500),
//            color = Black,
//            textAlign = TextAlign.Center,
//        ),
//        modifier = Modifier
//            .padding(start = 16.dp, bottom = 8.dp)
//    )
//
//    OutlinedTextField(
//        value = text,
//        onValueChange = onTextChange,
//        label = { Text(placeholder) },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(58.dp)
//            .padding(horizontal = 16.dp),
//        shape = RoundedCornerShape(8.dp),
//        colors = TextFieldDefaults.colors(
//            focusedTextColor = Color.Black,
//            unfocusedTextColor = Color.DarkGray,
//            focusedContainerColor = Color.Transparent,
//            unfocusedContainerColor = Color.Transparent,
//            cursorColor = Color.Gray,
//            focusedIndicatorColor = Color.Gray,
//            unfocusedIndicatorColor = Color.Gray
//        )
//    )
//}
//
//@Composable
//fun MealTypeSelector(
//    mealTypes: List<String>,
//    selectedMealType: String,
//    onMealTypeSelected: (String) -> Unit
//) {
//    Row(
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        modifier = Modifier
//            .fillMaxWidth()
//    ) {
//        mealTypes.forEach { meal ->
//            Button(
//                onClick = { onMealTypeSelected(meal) },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Transparent
//                ),
//                contentPadding = PaddingValues(0.dp),
//                modifier = Modifier
//                    .border(
//                        width = 1.dp,
//                        color = if (selectedMealType == meal) Orange01 else Color.LightGray,
//                        shape = RoundedCornerShape(8.dp)
//                    )
//                    .padding(0.5.dp)
//                    .width(60.dp)
//                    .height(40.dp)
//            ) {
//                Text(
//                    text = meal,
//                    style = TextStyle(
//                        fontSize = 14.sp,
//                        lineHeight = 20.sp,
//                        fontFamily = FontFamily(Font(R.font.notosans)),
//                        fontWeight = FontWeight(400),
//                        color = if (selectedMealType == meal) Orange01 else Color.LightGray,
//                        textAlign = TextAlign.Center
//                    )
//                )
//            }
//        }
//    }
//}
//
//
//@Preview
//@Composable
//fun MealInputScreenPreview() {
//    MealInputScreen(viewModel = MealInputViewModel())
//}