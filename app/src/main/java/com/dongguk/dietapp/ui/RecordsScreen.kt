package com.dongguk.dietapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dongguk.dietapp.ui.theme.LightGray
import com.dongguk.dietapp.ui.theme.MurkyOrange

@Composable
fun RecordsScreen() {

}

@Composable
fun MonthSelector() {
    val months = listOf("2024 10", "2024 11", "2024 12", "2025 1")
    val selectedMonth = remember { mutableStateOf("2024 12") }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(17.dp)
    ) {
        items(months) { month ->
            MonthItem(
                month = month,
                isSelected = month == selectedMonth.value,
                onClick = { selectedMonth.value = month }
            )
        }
    }
}

@Composable
fun MonthItem(month: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(69.dp)
            .height(78.dp)
            .clickable(onClick = onClick)
            .background(
                color = if (isSelected) MurkyOrange else LightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 3.dp,
                color = if (isSelected) Color(0xFFFFE9D0) else Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = month,
            color = if (isSelected) Color.White else Color.Black,
//            style = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun MonthSelectorPreview() {
    MonthSelector()
}
