package com.example.dinnergenerator.interfaces

import com.example.dinnergenerator.models.netwrok.meals.MealCategory

interface CategoryClickListener {

    fun onCategoryAdd(mealCategory: MealCategory, position:Int)
    fun onCategoryRemoved()
}