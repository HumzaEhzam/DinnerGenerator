package com.example.dinnergenerator.repository


import com.example.dinnergenerator.models.netwrok.drinks.DetailDrinkResponse
import com.example.dinnergenerator.models.netwrok.drinks.DrinkCategoryResponse
import com.example.dinnergenerator.models.netwrok.drinks.DrinkResponse
import com.example.dinnergenerator.models.netwrok.meals.DetailMealResponse
import com.example.dinnergenerator.models.netwrok.meals.MealCategoryResponse
import com.example.dinnergenerator.models.netwrok.meals.MealsResponse
import com.example.dinnergenerator.network.DrinksApi
import com.example.dinnergenerator.network.MealApi
import com.example.dinnergenerator.network.ResultWrapper
import com.example.dinnergenerator.utils.CONSTANTS.DRINK_PROVIDER
import com.example.dinnergenerator.utils.CONSTANTS.MEAL_PROVIDER
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class ApiDataRepository @Inject constructor() {
    @Inject
    @Named(MEAL_PROVIDER)
    lateinit var mealRetrofit: Retrofit

    @Inject
    @Named(DRINK_PROVIDER)
    lateinit var drinkRetrofit: Retrofit
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    /******************* Meals ****************/
    suspend fun getMealCategory(): ResultWrapper<MealCategoryResponse> {
        return safeApiCall(dispatcher) {
            mealRetrofit.create(MealApi::class.java).getMealCategories()
        }
    }

    suspend fun getMealByCategory(category: String): ResultWrapper<MealsResponse> {
        return safeApiCall(dispatcher) {
            mealRetrofit.create(MealApi::class.java).getMealByCategory(category)
        }
    }

    suspend fun getMealByID(id: String): ResultWrapper<DetailMealResponse> {
        return safeApiCall(dispatcher) {
            mealRetrofit.create(MealApi::class.java).getMealByID(id)
        }
    }

    /******************* Drinks ****************/

    suspend fun getDrinksCategory(): ResultWrapper<DrinkCategoryResponse> {
        return safeApiCall(dispatcher) {
            drinkRetrofit.create(DrinksApi::class.java).getDrinkCategories()
        }
    }

    suspend fun getDrinkByCategory(category: String): ResultWrapper<DrinkResponse> {
        return safeApiCall(dispatcher) {
            drinkRetrofit.create(DrinksApi::class.java).getDrinkByCategory(category)
        }
    }

    suspend fun getDrinkByID(id: String): ResultWrapper<DetailDrinkResponse> {
        return safeApiCall(dispatcher) {
            drinkRetrofit.create(DrinksApi::class.java).getDrinkByID(id)
        }
    }

}