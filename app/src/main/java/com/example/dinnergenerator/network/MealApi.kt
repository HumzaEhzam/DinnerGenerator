package com.example.dinnergenerator.network

import com.example.dinnergenerator.models.netwrok.meals.DetailMealResponse
import com.example.dinnergenerator.models.netwrok.meals.MealCategoryResponse
import com.example.dinnergenerator.models.netwrok.meals.MealsResponse
import com.example.dinnergenerator.utils.CONSTANTS.MEAL_CATEGORY_END_POINT
import com.example.dinnergenerator.utils.CONSTANTS.MEAL_OR_DRINK_BY_CATEGORY_END_POINT
import com.example.dinnergenerator.utils.CONSTANTS.MEAL_OR_DRINK_BY_ID_END_POINT
import retrofit2.http.GET
import retrofit2.http.Query


interface MealApi {

    /****************************   category **********************/

    @GET(MEAL_CATEGORY_END_POINT)
    suspend fun getMealCategories(): MealCategoryResponse

    @GET(MEAL_OR_DRINK_BY_CATEGORY_END_POINT)
    suspend fun getMealByCategory(@Query("c") category: String): MealsResponse
    @GET(MEAL_OR_DRINK_BY_ID_END_POINT)
    suspend fun getMealByID(@Query("i") id: String): DetailMealResponse


}