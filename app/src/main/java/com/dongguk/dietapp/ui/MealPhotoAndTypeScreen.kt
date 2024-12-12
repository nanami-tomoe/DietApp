package com.dongguk.dietapp.ui

import android.app.DatePickerDialog
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dongguk.dietapp.R
import com.dongguk.dietapp.data.DataSource
import com.dongguk.dietapp.ui.theme.Ivory
import com.dongguk.dietapp.ui.theme.Orange01
import com.dongguk.dietapp.viewmodel.MealRecordViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun MealPhotoAndTypeScreen(
    viewModel: MealRecordViewModel = MealRecordViewModel(),
    onNextClicked: () -> Unit
) {
    val currentMeal = viewModel.currentMeal
    val mealTypes = DataSource.locationsByMealType.keys.toList()
    val context = LocalContext.current

    var selectedDate by remember { mutableStateOf(currentMeal.date) }
    var showDatePicker by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                viewModel.updatePhoto(uri.toString())
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(
            text = "식사 입력하기",
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 22.5.sp,
                fontFamily = FontFamily(Font(R.font.nanum_square_neo_bd)),
                fontWeight = FontWeight(700),
                color = Color.Black,
            ),
            modifier = Modifier
                .padding(top = 36.dp, bottom = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (currentMeal.photoUri.isNotEmpty()) {
                AsyncImage(
                    model = currentMeal.photoUri,
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.image_ic),
                    contentDescription = "Default Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
            }
        }

        Button(
            onClick = { launcher.launch("image/*") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Orange01
            ),
            elevation = ButtonDefaults.buttonElevation(0.dp),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "음식 사진을 선택하세요.",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 15.sp,
                    fontFamily = FontFamily(Font(R.font.nanum_square_neo_rg)),
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
                fontFamily = FontFamily(Font(R.font.nanum_square_neo_rg)),
                fontWeight = FontWeight(500),
                color = Color.Black,
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(),
        ) {
            mealTypes.forEach { meal ->
                Button(
                    onClick = { viewModel.selectMealType(meal) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = if (currentMeal.selectedMealType == meal) Orange01 else Color.LightGray,
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
                            fontFamily = FontFamily(Font(R.font.nanum_square_neo_rg)),
                            fontWeight = FontWeight(400),
                            color = if (currentMeal.selectedMealType == meal) Orange01 else Color.Black,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "식사 날짜 입력",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.nanum_square_neo_rg)),
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
                .clickable { showDatePicker = true },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (selectedDate.isEmpty()) "MM/DD/YYYY" else selectedDate,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.nanum_square_neo_rg)),
                    fontWeight = FontWeight.Normal,
                    color = if (selectedDate.isEmpty()) Color.Gray else Color.Black
                )
            )
        }

        if (showDatePicker) {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    selectedDate = convertMillisToDate(calendar.timeInMillis)
                    viewModel.updateDate(selectedDate)
                    showDatePicker = false
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .width(358.dp)
                .height(56.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                viewModel.addMeal()
                onNextClicked() // 클릭 시 콜백 호출
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange01,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Next",
                style = TextStyle(
                    fontFamily = Jalnan,
                    fontSize = 16.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(78.dp))

    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Preview
@Composable
fun MealPhotoAndTypePreview() {
    MealPhotoAndTypeScreen(viewModel = MealRecordViewModel(), onNextClicked = {})
}