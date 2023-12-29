package com.example.dinnergenerator.view.models


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinnergenerator.models.netwrok.drinks.DetailDrinkResponse
import com.example.dinnergenerator.models.netwrok.drinks.DrinkResponse
import com.example.dinnergenerator.models.netwrok.meals.DetailMealResponse
import com.example.dinnergenerator.models.netwrok.meals.MealsResponse
import com.example.dinnergenerator.network.ResultWrapper
import com.example.dinnergenerator.repository.ApiDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class DinnerViewModel @Inject constructor(private val dataRepository: ApiDataRepository) :
    ViewModel() {

    val snakBarMessage = MutableLiveData<String>()
    val progressBar = MutableLiveData<Boolean>()
    var mealByCategoryResponse = MutableLiveData<MealsResponse?>()
    var mealByIDResponse = MutableLiveData<DetailMealResponse?>()
    var drinksByCategoryResponse = MutableLiveData<DrinkResponse?>()
    var drinksByIDResponse = MutableLiveData<DetailDrinkResponse?>()

    //    val mobileNum: ObservableField<String> = ObservableField("")
//    val passWord: ObservableField<String> = ObservableField("")
//    val loginResponse: MutableLiveData<LoginResponse?> = MutableLiveData()
//    val mobileNumberError: MutableLiveData<Boolean> = MutableLiveData()
//    val passwordError: MutableLiveData<Boolean> = MutableLiveData()
//
//
//
//    fun onClick(key: Int=0) {
//
//        if (validateFields()) {
//            mobileNumberError.value = false
//            passwordError.value = false
//            login()
//        } else {
//            mobileNumberError.value = true
//            passwordError.value = true
//        }
//    }
//
    fun getMealByCategory(category: String) {
        viewModelScope.launch {
            showProgressBar(true)
            dataRepository.getMealByCategory(category)
                .let { response ->
                    showProgressBar(false)

                    when (response) {
                        is ResultWrapper.Success -> {
                            mealByCategoryResponse.value = response.value
                        }

                        else -> handleErrorType(response)
                    }
                }
        }
    }

    fun getDrinkByCategory(category: String) {
        viewModelScope.launch {
            showProgressBar(true)
            dataRepository.getDrinkByCategory(category)
                .let { response ->
                    showProgressBar(false)

                    when (response) {
                        is ResultWrapper.Success -> {
                            drinksByCategoryResponse.value = response.value
                        }

                        else -> handleErrorType(response)
                    }
                }
        }
    }

    fun getDrinkById(id: String) {
        viewModelScope.launch {
            showProgressBar(true)
            dataRepository.getDrinkByID(id)
                .let { response ->
                    showProgressBar(false)

                    when (response) {
                        is ResultWrapper.Success -> {
                            drinksByIDResponse.value = response.value
                        }

                        else -> handleErrorType(response)
                    }
                }
        }
    }

    fun getMealById(id: String) {
        viewModelScope.launch {
            showProgressBar(true)
            dataRepository.getMealByID(id)
                .let { response ->
                    showProgressBar(false)

                    when (response) {
                        is ResultWrapper.Success -> {
                            mealByIDResponse.value = response.value
                        }

                        else -> handleErrorType(response)
                    }
                }
        }
    }

    private fun showSnackBarMessage(message: String) {
        snakBarMessage.value = message
    }


    private fun showProgressBar(show: Boolean) {
        progressBar.value = show
    }

    private fun handleErrorType(error: ResultWrapper<Any>) {
        showProgressBar(false)
        when (error) {
            is ResultWrapper.NetworkError ->
                showSnackBarMessage("Internet not available")

            is ResultWrapper.GenericError ->
                showSnackBarMessage("" + error.error?.message)

            else -> {

            }
        }
    }
}