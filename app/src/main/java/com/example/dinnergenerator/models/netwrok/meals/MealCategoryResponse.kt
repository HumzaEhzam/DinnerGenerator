package com.example.dinnergenerator.models.netwrok.meals

import java.io.Serializable

data class MealCategoryResponse (
    val categories: ArrayList<MealCategory> = arrayListOf()
):Serializable