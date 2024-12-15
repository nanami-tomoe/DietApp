package com.dongguk.dietapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dongguk.dietapp.R

@Composable
fun BottomNavigationBar() {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        // 하단 네비게이션 바의 전체 배경
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 0.dp, spotColor = Color(0x26000000), ambientColor = Color(0x26000000))
                .height(53.dp)
                .background(color = Color.White)
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.Bottom,
        ) {
            BottomNavItem(iconRes = R.drawable.ic_magnifier, text = "식사 분석", selected = true)
            Spacer(modifier = Modifier.width(50.dp)) // 가운데 여백
            BottomNavItem(iconRes = R.drawable.ic_record, text = "식사 기록", selected = false)
        }

        // 가운데 원형 버튼 추가
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(52.dp) // 원의 크기 설정
                .offset(y = (-20).dp) // 튀어나오게 설정
//                .shadow( // 그림자 추가
//                    elevation = 8.dp,
//                    shape = CircleShape,
//                    spotColor = Color(0x26000000),
//                    ambientColor = Color(0x26000000)
//                )
                .shadow(elevation = 0.dp, spotColor = Color(0x26000000), ambientColor = Color(0x26000000))
                .clip(CircleShape) // 원형으로 클리핑
                .background(Color(0xFFFF7A00)) // 주황색 배
                .border(width = 4.dp, color = Color(0xFFFCFAEF), shape = RoundedCornerShape(size = 100.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "plus icon",
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
fun BottomNavItem(iconRes: Int, text: String, selected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .size(24.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = text,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                color = if (selected) Color(0xFFFF7A00) else Color(0x80484C52)
            )
        )
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}