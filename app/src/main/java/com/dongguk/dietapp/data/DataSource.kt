package com.dongguk.dietapp.data

object DataSource {
    // 식사 종류 리스트
    val mealTypes = listOf(
        "조식", "중식", "석식", "간식", "음료"
    )

    // 식사 장소 리스트
    val mealLocations = mapOf(
        "조식" to listOf("식당1", "식당2"),
        "중식" to listOf("식당1", "식당3"),
        "석식" to listOf("식당2", "식당4"),
        "간식" to listOf("카페1", "카페2"),
        "음료" to listOf("카페1", "카페3")
    )

    // 음식과 반찬 리스트
    val foodOptions = mapOf(
        "식당1" to listOf(
            Pair("된장찌개", listOf("김치", "콩나물")),
            Pair("불고기 정식", listOf("깍두기", "미역국"))
        ),
        "식당2" to listOf(
            Pair("햄 에그 샌드위치", listOf()),
            Pair("아메리칸 브렉퍼스트", listOf("샐러드", "토스트"))
        ),
        "카페1" to listOf(
            Pair("초코 케이크", null),
            Pair("크로와상", null)
        )
    )
}