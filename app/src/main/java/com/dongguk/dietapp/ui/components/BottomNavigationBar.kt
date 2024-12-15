package com.dongguk.dietapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dongguk.dietapp.R


// 아직 개발 중입니다. 추후에 리팩토링 하면서 추가할 예정입니다.


@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        // 네비게이션 아이템
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
            // 식사 분석 버튼
            BottomNavItem(
                iconRes = R.drawable.ic_magnifier,
                label = "식사 분석",
                onClick = {
                    navController.navigate("meal_analysis")
                }
            )

            Spacer(modifier = Modifier.width(80.dp)) // 가운데 버튼 여백

            // 식사 기록 버튼
            BottomNavItem(
                iconRes = R.drawable.ic_record,
                label = "식사 기록",
                onClick = {
                    navController.navigate("meal_record")
                }
            )
        }

        // 가운데 원형 버튼
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
            IconButton(
                onClick = {
                    navController.navigate("meal_photo_type")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Add Meal",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(iconRes: Int, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = Color(0xFF484C52)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF484C52)
        )
    }
}

@Composable
@Preview
fun BottomNavigationBarPreview() {
//    BottomNavigationBar()
}