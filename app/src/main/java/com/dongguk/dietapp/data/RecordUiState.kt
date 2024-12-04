package com.dongguk.dietapp.data

data class RecordUiState(
    /** 음식 사진의 URI */
    val photoUri: String = "", // 사진 경로 저장
    /** 선택된 식사 종류 */
    val selectedMealType: String = "",
    /** 선택된 식사 장소 */
    val selectedMealLocation: String = "",
    /** 선택된 음식 */
    val selectedMeal: String = "",
    /** 선택된 반찬 리스트 */
    val selectedSideDishes: List<String>? = null,
    /** 가격 */
    val price: Int = 0
)
