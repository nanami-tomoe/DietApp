package com.dongguk.dietapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SelectMealTypeViewModel : ViewModel() {
    // 식사 종류 상태 관리
    var selectedMealType by mutableStateOf("조식")
}