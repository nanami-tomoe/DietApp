package com.dongguk.dietapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dongguk.dietapp.R
import com.dongguk.dietapp.ui.theme.Orange01

val Nanum = FontFamily(Font(R.font.nanum_square_neo_rg))
val NanumBold = FontFamily(Font(R.font.nanum_square_neo_bd))
val Jalnan = FontFamily(Font(R.font.jalnan))

@Composable
fun StartRecordScreen(
    onStartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 48.dp)
            .background(Color(0xFFFEF3E2)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.aco), contentDescription = "aco")
        Text(
            text = "여러분의 교내 식사 습관을 관리하고 기록하세요!",
            style = TextStyle(
                fontFamily = Jalnan,
                fontSize = 28.sp,
                color = Color(0xFF402E32)
            ),
            modifier = Modifier.padding(top = 27.dp, start = 19.dp, end = 19.dp)
        )
        Text(
            text = "\"동국한끼는 식사 기록, 비용 계산, 칼로리 확인까지 한번에 관리할 수 있도록 도와줍니다. 매일 교내 식당과 카페에서 먹은 식사 기록을 기록하고 이를 토대로 개인의 식단을 관리할 수 있습니다.\"",
            style = TextStyle(
                fontFamily = Nanum,
                fontSize = 16.sp,
                color = Color(0xFF402E32).copy(alpha = 0.5f)
            ),
            modifier = Modifier.padding(top = 10.dp, bottom = 27.dp, start = 19.dp, end = 19.dp)
        )

        Button(
            modifier = Modifier
                .width(358.dp) // 버튼 너비
                .height(64.dp) // 버튼 높이
                .padding(horizontal = 16.dp), // 좌우 여백
            onClick = onStartClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange01,
                contentColor = Color.White // 텍스트 색상
            ),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                text = "Start",
                style = TextStyle(
                    fontFamily = Jalnan, // 커스텀 폰트 적용
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp // 글자 크기
                )
            )
        }
    }
}

@Preview
@Composable
fun StartRecordScreenPreview() {
    StartRecordScreen({})
}
