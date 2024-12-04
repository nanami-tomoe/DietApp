package com.dongguk.dietapp.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class RecordUiState(
    /** 음식 사진의 URI */
    val photoUri: String = "", // 사진 경로 저장
    /** 선택된 식사 종류 */
    val selectedMealType: String = "",
    /** 선택된 식사 장소 */
    val selectedMealLocation: String = "",
    /** 선택된 음식 */
    val selectedMeal: String = "",
    /** 선택된 반찬 */
    val selectedSideDishes: String? = null, // null 가능
    /** 음식 칼로리 */
    val mealCalories: Int = 0,
    /** 반찬 칼로리 */
    val sideDishCalories: Int? = null, // 반찬 칼로리 초기값 null
    /** 가격 */
    val price: Int = 0,
    /** 음식에 대한 소감 또는 평 */
    val review: String = "",
    /** 날짜 */
    val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
)