package com.dongguk.dietapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MealInputViewModel : ViewModel() {

    // 입력 필드 데이터 상태
    var location by mutableStateOf("")
    var menuName by mutableStateOf("")
    var sideDish by mutableStateOf("")
    var snackName by mutableStateOf("")
    var drinkName by mutableStateOf("")

}