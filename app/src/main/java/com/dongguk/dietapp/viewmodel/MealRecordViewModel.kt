package com.dongguk.dietapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dongguk.dietapp.data.RecordUiState

class MealRecordViewModel : ViewModel() {
    var uiState by mutableStateOf(RecordUiState())
        private set

    // 식사 종류 선택
    fun selectMealType(mealType: String) {
        uiState = uiState.copy(
            selectedMealType = mealType,
            selectedMealLocation = "",
            selectedMeal = "",
            selectedSideDishes = null,
            price = 0
        )
    }

    // 식사 장소 선택
    fun selectMealLocation(location: String) {
        uiState = uiState.copy(
            selectedMealLocation = location,
            selectedMeal = "",
            selectedSideDishes = null,
            price = 0
        )
    }

    // 음식 선택
    fun selectMeal(meal: String, sideDishes: List<String>?, price: Int) {
        uiState = uiState.copy(
            selectedMeal = meal,
            selectedSideDishes = sideDishes,
            price = price
        )
    }
}