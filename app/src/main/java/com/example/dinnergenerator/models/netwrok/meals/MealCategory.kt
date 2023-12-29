package com.example.dinnergenerator.models.netwrok.meals

import java.io.Serializable

data class MealCategory(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String,
    var isChecked:Boolean = false
):Serializable