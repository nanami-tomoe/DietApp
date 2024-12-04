package com.dongguk.dietapp.data

object DataSource {
    // 식사 종류별 장소 리스트
    val locationsByMealType = mapOf(
        "조식" to listOf("식당1", "식당2"),
        "중식" to listOf("식당1", "식당2"),
        "석식" to listOf("식당1", "식당2"),
        "간식" to listOf("카페1", "카페2", "카페3"),
        "음료" to listOf("카페1", "카페2", "카페3")
    )

    // 식사 장소별 음식 이름 리스트
    val foodNamesByLocation = mapOf(
        "식당1" to listOf("된장찌개", "불고기 정식"),
        "식당2" to listOf("햄 에그 샌드위치", "아메리칸 브렉퍼스트"),
        "카페1" to listOf("초코 케이크", "크로와상"),
        "카페2" to emptyList(), // 간식/음료 추가 가능
        "카페3" to emptyList()  // 간식/음료 추가 가능
    )

    // 식사 장소별 반찬 이름 리스트
    val sideDishesByLocation = mapOf(
        "식당1" to listOf("김치", "콩나물", "깍두기", "미역국"),
        "식당2" to listOf("샐러드", "토스트"),
        "카페1" to emptyList(), // 카페는 반찬이 없음
        "카페2" to emptyList(),
        "카페3" to emptyList()
    )

    // 음식별 칼로리 정보
    val caloriesByFood = mapOf(
        "된장찌개" to 450,
        "불고기 정식" to 600,
        "햄 에그 샌드위치" to 300,
        "아메리칸 브렉퍼스트" to 550,
        "초코 케이크" to 400,
        "크로와상" to 320
    )

    // 반찬별 칼로리 정보
    val caloriesBySideDish = mapOf(
        "김치" to 15,
        "콩나물" to 20,
        "깍두기" to 30,
        "미역국" to 50,
        "샐러드" to 70,
        "토스트" to 100
    )
}