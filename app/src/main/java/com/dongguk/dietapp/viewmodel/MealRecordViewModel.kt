package com.dongguk.dietapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dongguk.dietapp.data.DataSource
import com.dongguk.dietapp.data.RecordUiState

class MealRecordViewModel : ViewModel() {
    // ViewModel의 상태
    var meals by mutableStateOf<List<RecordUiState>>(emptyList()) // 저장된 식사 목록
        private set

    // 현재 입력 중인 식사 상태
    var currentMeal by mutableStateOf(RecordUiState())
        private set

    // 음식 사진 업데이트
    fun updatePhoto(photo: String) {
        currentMeal = currentMeal.copy(photoUri = photo)
    }

    // 식사 종류 선택
    fun selectMealType(mealType: String) {
        currentMeal = currentMeal.copy(
            selectedMealType = mealType,
            selectedMealLocation = "",
            selectedMeal = "",
            selectedSideDishes = null,
            mealCalories = 0,
            sideDishCalories = null,
            price = 0,
            review = ""
        )
    }

    // 식사 장소 선택
    fun selectMealLocation(location: String) {
        currentMeal = currentMeal.copy(
            selectedMealLocation = location,
            selectedMeal = "",
            selectedSideDishes = null,
            mealCalories = 0,
            sideDishCalories = null
        )
    }

    // 음식 선택
    fun selectMeal(meal: String) {
        val calories = DataSource.caloriesByFood[meal] ?: 0
        currentMeal = currentMeal.copy(
            selectedMeal = meal,
            mealCalories = calories,
            selectedSideDishes = null,
            sideDishCalories = null
        )
    }

    // 반찬 선택
    fun selectSideDish(sideDish: String) {
        val calories = DataSource.caloriesBySideDish[sideDish] ?: 0
        currentMeal = currentMeal.copy(
            selectedSideDishes = sideDish,
            sideDishCalories = calories
        )
    }

    // 날짜 업데이트
    fun updateDate(newDate: String) {
        currentMeal = currentMeal.copy(date = newDate)
    }

    // 가격 업데이트
    fun updatePrice(newPrice: Int) {
        currentMeal = currentMeal.copy(price = newPrice)
    }

    // 소감/평 업데이트
    fun updateReview(newReview: String) {
        currentMeal = currentMeal.copy(review = newReview)
    }

    fun addMeal() {
        Log.d("MealRecordViewModel", "Adding Meal: $currentMeal")
        if (currentMeal.selectedMealType.isNotEmpty() &&
            currentMeal.selectedMealLocation.isNotEmpty() &&
            currentMeal.selectedMeal.isNotEmpty() &&
            currentMeal.price > 0 &&
            currentMeal.mealCalories > 0
        ) {
            meals = meals + currentMeal
            Log.d("MealRecordViewModel", "Meal added. Meals: $meals")
            resetCurrentMeal()
        } else {
            Log.e("MealRecordViewModel", "Meal not added. CurrentMeal: $currentMeal")
        }
    }

    // 현재 입력 상태 초기화
    private fun resetCurrentMeal() {
        currentMeal = RecordUiState()
    }
}
