package com.example.dinnergenerator.network

import com.example.dinnergenerator.models.netwrok.drinks.DetailDrinkResponse
import com.example.dinnergenerator.models.netwrok.drinks.DrinkCategoryResponse
import com.example.dinnergenerator.models.netwrok.drinks.DrinkResponse
import com.example.dinnergenerator.models.netwrok.meals.MealCategoryResponse
import com.example.dinnergenerator.models.netwrok.meals.MealsResponse
import com.example.dinnergenerator.utils.CONSTANTS
import com.example.dinnergenerator.utils.CONSTANTS.DRINK_CATEGORY_END_POINT
import com.example.dinnergenerator.utils.CONSTANTS.MEAL_CATEGORY_END_POINT
import com.example.dinnergenerator.utils.CONSTANTS.MEAL_OR_DRINK_BY_CATEGORY_END_POINT
import retrofit2.http.GET
import retrofit2.http.Query


interface DrinksApi {

    /****************************   category **********************/

    @GET(DRINK_CATEGORY_END_POINT)
    suspend fun getDrinkCategories(): DrinkCategoryResponse

    @GET(MEAL_OR_DRINK_BY_CATEGORY_END_POINT)
    suspend fun getDrinkByCategory(@Query("c") category: String): DrinkResponse

    @GET(CONSTANTS.MEAL_OR_DRINK_BY_ID_END_POINT)
    suspend fun getDrinkByID(@Query("i") id: String): DetailDrinkResponse

}