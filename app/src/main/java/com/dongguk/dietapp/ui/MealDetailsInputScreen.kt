package com.dongguk.dietapp.ui

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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dongguk.dietapp.R
import com.dongguk.dietapp.data.DataSource
import com.dongguk.dietapp.data.RecordUiState
import com.dongguk.dietapp.ui.theme.Ivory
import com.dongguk.dietapp.ui.theme.Orange01
import com.dongguk.dietapp.ui.theme.Orange02
import com.dongguk.dietapp.viewmodel.MealRecordViewModel

@Composable
fun MealDetailsInputScreen(
    viewModel: MealRecordViewModel = MealRecordViewModel(),
    onSubmitClicked: () -> Unit
) {
    val currentMeal = viewModel.currentMeal
    val locations = DataSource.locationsByMealType[currentMeal.selectedMealType] ?: listOf()
    val foodOptions = DataSource.foodNamesByLocation[currentMeal.selectedMealLocation] ?: listOf()
    val sideDishes = DataSource.sideDishesByLocation[currentMeal.selectedMealLocation] ?: listOf()

    var isLocationDropdownExpanded by remember { mutableStateOf(false) }
    var isFoodDropdownExpanded by remember { mutableStateOf(false) }
    var isSideDishDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Ivory),
        verticalArrangement = Arrangement.SpaceBetween
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
                .align(Alignment.CenterHorizontally)
        )

        MealLocationSelector(
            currentMeal = currentMeal,
            locations = locations,
            onLocationSelected = { location -> viewModel.selectMealLocation(location) },
            isDropdownExpanded = isLocationDropdownExpanded,
            onDropdownToggle = { isExpanded -> isLocationDropdownExpanded = isExpanded }
        )

        Spacer(modifier = Modifier.height(25.dp))

        if (currentMeal.selectedMealType != "간식" && currentMeal.selectedMealType != "음료") {

            MealSelector(
                currentMeal = currentMeal,
                meals = foodOptions,
                onMealSelected = { meal -> viewModel.selectMeal(meal) },
                isDropdownExpanded = isFoodDropdownExpanded,
                onDropdownToggle = { isExpanded -> isFoodDropdownExpanded = isExpanded }
            )

            Spacer(modifier = Modifier.height(25.dp))

            SideDishSelector(
                currentMeal = currentMeal,
                sideDishes = sideDishes,
                onSideDishSelected = { sideDish -> viewModel.selectSideDish(sideDish) },
                isDropdownExpanded = isSideDishDropdownExpanded,
                onDropdownToggle = { isExpanded -> isSideDishDropdownExpanded = isExpanded }
            )
        } else {
            SnackOrDrinkSelector(
                currentMeal = currentMeal,
                meals = foodOptions,
                onMealSelected = { meal -> viewModel.selectMeal(meal) },
                isDropdownExpanded = isFoodDropdownExpanded,
                onDropdownToggle = { isExpanded -> isFoodDropdownExpanded = isExpanded }
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        PriceInputField(
            price = currentMeal.price.toString() ?: "",
            onPriceChanged = { newPrice -> viewModel.updatePrice(newPrice.toInt()) }
        )

        Spacer(modifier = Modifier.height(25.dp))

        ReviewInputField(
            review = currentMeal.review,
            onReviewChanged = { newReview -> viewModel.updateReview(newReview) }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .width(358.dp)
                .height(56.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                viewModel.addMeal()
                onSubmitClicked()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange01,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Submit",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.notosansbold)),
                    fontSize = 16.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(78.dp))
    }
}

@Composable
fun MealLocationSelector(
    currentMeal: RecordUiState,
    locations: List<String>,
    onLocationSelected: (String) -> Unit,
    isDropdownExpanded: Boolean,
    onDropdownToggle: (Boolean) -> Unit
) {
    Text(
        text = "식사 장소 선택",
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(R.font.notosans)),
            fontWeight = FontWeight(500),
            color = Color.Black,
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier
            .padding(start = 16.dp, bottom = 8.dp)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .height(58.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = Color.Gray
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (currentMeal.selectedMealLocation.isEmpty()) "식사 장소를 선택해주세요." else currentMeal.selectedMealLocation,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.notosans)),
                    fontWeight = FontWeight.Normal,
                    color = if (currentMeal.selectedMealLocation.isEmpty()) Color.Gray else Color.Black
                )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Dropdown Arrow",
                colorFilter = ColorFilter.tint(Color.Gray),
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
                    .clickable { onDropdownToggle(!isDropdownExpanded) }
            )
        }
    }

    DropdownMenu(
        expanded = isDropdownExpanded,
        onDismissRequest = { onDropdownToggle(false) },
        modifier = Modifier
            .background(Orange02)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
        offset = DpOffset(130.dp, 260.dp), // y축으로 12dp만큼의 여유공간을 만들기
    ) {
        locations.forEach { location ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onLocationSelected(location)
                        onDropdownToggle(false)
                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = location,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.notosans)),
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )
            }
        }
    }
}

@Composable
fun MealSelector(
    currentMeal: RecordUiState,
    meals: List<String>,
    onMealSelected: (String) -> Unit,
    isDropdownExpanded: Boolean,
    onDropdownToggle: (Boolean) -> Unit
){
    Text(
        text = "음식 선택",
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(R.font.notosans)),
            fontWeight = FontWeight(500),
            color = Color.Black,
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier
            .padding(start = 16.dp, bottom = 8.dp)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .height(58.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = Color.Gray
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (currentMeal.selectedMeal.isEmpty()) "음식을 선택해주세요." else currentMeal.selectedMeal,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.notosans)),
                    fontWeight = FontWeight.Normal,
                    color = if (currentMeal.selectedMeal.isEmpty()) Color.Gray else Color.Black
                )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Dropdown Arrow",
                colorFilter = ColorFilter.tint(Color.Gray),
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
                    .clickable { onDropdownToggle(!isDropdownExpanded) }
            )
        }
    }

    DropdownMenu(
        expanded = isDropdownExpanded,
        onDismissRequest = { onDropdownToggle(false) },
        modifier = Modifier
            .background(Orange02)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
        offset = DpOffset(130.dp, 400.dp), // y축으로 12dp만큼의 여유공간을 만들기
    ) {
        meals.forEach { meal ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onMealSelected(meal)
                        onDropdownToggle(false)
                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = meal,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.notosans)),
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )
            }
        }
    }
}

@Composable
fun SnackOrDrinkSelector(
    currentMeal: RecordUiState,
    meals: List<String>,
    onMealSelected: (String) -> Unit,
    isDropdownExpanded: Boolean,
    onDropdownToggle: (Boolean) -> Unit
) {
    Text(
        text = "간식/음료 선택",
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(R.font.notosans)),
            fontWeight = FontWeight(500),
            color = Color.Black,
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier
            .padding(start = 16.dp, bottom = 8.dp)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .height(58.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = Color.Gray
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (currentMeal.selectedMeal.isEmpty()) "간식 또는 음료를 선택해주세요." else currentMeal.selectedMeal,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.notosans)),
                    fontWeight = FontWeight.Normal,
                    color = if (currentMeal.selectedMeal.isEmpty()) Color.Gray else Color.Black
                )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Dropdown Arrow",
                colorFilter = ColorFilter.tint(Color.Gray),
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
                    .clickable { onDropdownToggle(!isDropdownExpanded) }
            )
        }
    }

    DropdownMenu(
        expanded = isDropdownExpanded,
        onDismissRequest = { onDropdownToggle(false) },
        modifier = Modifier
            .background(Orange02)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
        offset = DpOffset(130.dp, 400.dp), // y축으로 12dp만큼의 여유공간을 만들기
    ) {
        meals.forEach { meal ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onMealSelected(meal)
                        onDropdownToggle(false)
                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = meal,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.notosans)),
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )
            }
        }
    }
}


@Composable
fun SideDishSelector(
    currentMeal: RecordUiState,
    sideDishes: List<String>,
    onSideDishSelected: (String) -> Unit,
    isDropdownExpanded: Boolean,
    onDropdownToggle: (Boolean) -> Unit
) {
    Text(
        text = "반찬 선택",
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(R.font.notosans)),
            fontWeight = FontWeight(500),
            color = Color.Black,
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier
            .padding(start = 16.dp, bottom = 8.dp)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .height(58.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = Color.Gray
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (currentMeal.selectedSideDishes.isNullOrEmpty()) "반찬을 선택해주세요." else currentMeal.selectedSideDishes,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.notosans)),
                    fontWeight = FontWeight.Normal,
                    color = if (currentMeal.selectedSideDishes.isNullOrEmpty()) Color.Gray else Color.Black
                )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Dropdown Arrow",
                colorFilter = ColorFilter.tint(Color.Gray),
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
                    .clickable { onDropdownToggle(!isDropdownExpanded) }
            )
        }
    }

    DropdownMenu(
        expanded = isDropdownExpanded,
        onDismissRequest = { onDropdownToggle(false) },
        modifier = Modifier
            .background(Orange02)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
        offset = DpOffset(130.dp, 500.dp), // y축으로 12dp만큼의 여유공간을 만들기
    ) {
        sideDishes.forEach { sideDish ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSideDishSelected(sideDish)
                        onDropdownToggle(false)
                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = sideDish,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.notosans)),
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )
            }
        }
    }
}

@Composable
fun PriceInputField(
    price: String,
    onPriceChanged: (String) -> Unit
) {
    // "가격 입력" 텍스트
    Text(
        text = "가격 입력",
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(R.font.notosans)),
            fontWeight = FontWeight(500),
            color = Color.Black,
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier
            .padding(start = 16.dp, bottom = 8.dp)
    )

    // 입력 필드 박스
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .height(58.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = Color.Gray
            )
            .background(Color.White)
    ) {
        // 가격 입력 필드
        BasicTextField(
            value = price,
            onValueChange = { newPrice ->
                // 숫자만 입력 가능하도록 필터링
                if (newPrice.all { it.isDigit() }) {
                    onPriceChanged(newPrice)
                }
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.notosans)),
                fontWeight = FontWeight.Normal,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxSize()
                .background(Ivory)
                .padding(start = 16.dp, end = 16.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (price.isEmpty()) {
                            Text(
                                text = "가격을 입력해주세요.",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.notosans)),
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Gray
                                )
                            )
                        }
                        innerTextField()
                    }
                    Text(
                        text = "원",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.notosans)),
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    )
                }
            }
        )
    }
}

@Composable
fun ReviewInputField(
    review: String,
    onReviewChanged: (String) -> Unit
) {
    // "리뷰 입력" 텍스트
    Text(
        text = "리뷰 입력",
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(R.font.notosans)),
            fontWeight = FontWeight(500),
            color = Color.Black,
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier
            .padding(start = 16.dp, bottom = 8.dp)
    )

    // 입력 필드 박스
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = Color.Gray
            )
            .background(Ivory)
            .heightIn(min = 58.dp) // 최소 높이 58.dp
            .verticalScroll(rememberScrollState()) // 리뷰 입력 필드 스크롤 추가
    ) {
        // 리뷰 입력 필드
        BasicTextField(
            value = review,
            onValueChange = { newReview -> onReviewChanged(newReview) },
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.notosans)),
                fontWeight = FontWeight.Normal,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 19.dp, bottom = 19.dp),
            decorationBox = { innerTextField ->
                if (review.isEmpty()) {
                    Text(
                        text = "리뷰를 입력해주세요.",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.notosans)),
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    )
                }
                innerTextField()
            }
        )
    }
}

@Preview
@Composable
fun MealDetailsInputScreenPreview() {
    MealDetailsInputScreen(viewModel = MealRecordViewModel(), onSubmitClicked = {})
}