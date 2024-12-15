package com.dongguk.dietapp.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dongguk.dietapp.data.DataSource
import com.dongguk.dietapp.data.RecordUiState
import java.time.LocalDate
import java.time.LocalDate.*
import java.time.format.DateTimeFormatter

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
            currentMeal.mealCalories > 0 &&
            currentMeal.photoUri.isNotEmpty()
        ) {
            meals = meals + currentMeal
            Log.d("MealRecordViewModel", "Meal added. Meals: $meals")
            resetCurrentMeal()
        } else {
            Log.e("MealRecordViewModel", "Meal not added. CurrentMeal: $currentMeal")
        }
    }

    // 최근 한달 간의 각 식사 종류별 비용과 비율 계산
    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateMealTypeCostAndPercentageForLastMonth(): Map<String, Pair<Int, Float>> {
        val oneMonthAgo = now().minusMonths(1) // 한 달 전 날짜
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // 날짜 형식에 맞게 설정

        // 한 달 내 식사만 필터링
        val lastMonthMeals = meals.filter { meal ->
            val mealDate = parse(meal.date, formatter) // 날짜 파싱
            mealDate.isAfter(oneMonthAgo) || mealDate.isEqual(oneMonthAgo) // 한 달 이내인지 확인
        }

        // 식사 종류별 총 비용 계산
        val totalCost = lastMonthMeals.sumOf { it.price } // 전체 비용
        val mealTypeCostMap = lastMonthMeals.groupBy { it.selectedMealType }.mapValues { entry ->
            entry.value.sumOf { it.price } // 각 식사 종류의 총 비용 계산
        }

        // 식사 종류별 비용과 비율 계산
        return mealTypeCostMap.mapValues { (mealType, cost) ->
            val percentage = if (totalCost > 0) (cost.toFloat() / totalCost * 100) else 0f // 비율 계산
            Pair(cost, percentage)
        }
    }

    // 최근 한달 간의 칼로리 총량 계산
    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateTotalCaloriesForLastMonth(): Int {
        val oneMonthAgo = now().minusMonths(1) // 한 달 전 날짜
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // 날짜 형식에 맞게 설정

        return meals.filter { meal ->
            val mealDate = parse(meal.date, formatter) // 날짜 파싱
            mealDate.isAfter(oneMonthAgo) || mealDate.isEqual(oneMonthAgo) // 한 달 이내인지 확인
        }.sumOf { it.mealCalories + (it.sideDishCalories ?: 0) }
    }

    // 현재 입력 상태 초기화
    private fun resetCurrentMeal() {
        currentMeal = RecordUiState()
    }
}
