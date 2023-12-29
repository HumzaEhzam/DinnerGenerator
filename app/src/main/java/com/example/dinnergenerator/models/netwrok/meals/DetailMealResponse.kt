package com.example.dinnergenerator.models.netwrok.meals

import java.io.Serializable

data class DetailMealResponse(
    val meals: List<DetailMeal>
):Serializable