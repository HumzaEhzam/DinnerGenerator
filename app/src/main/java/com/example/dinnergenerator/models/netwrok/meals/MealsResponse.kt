package com.example.dinnergenerator.models.netwrok.meals

import java.io.Serializable

data class MealsResponse(
    val meals: List<Meal> = arrayListOf()
):Serializable