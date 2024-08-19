package com.app.reciperealm.models.remote

data class AllCategoryResponse(
    val meals: List<Meal>
) {
    data class Meal(
        val strCategory: String
    )
}