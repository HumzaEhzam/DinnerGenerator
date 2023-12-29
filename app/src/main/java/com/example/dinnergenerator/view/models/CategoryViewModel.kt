package com.example.dinnergenerator.view.models



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinnergenerator.models.netwrok.drinks.DrinkCategoryResponse
import com.example.dinnergenerator.models.netwrok.meals.MealCategoryResponse
import com.example.dinnergenerator.network.ResultWrapper
import com.example.dinnergenerator.repository.ApiDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val dataRepository: ApiDataRepository) :ViewModel() {

 val snakBarMessage = MutableLiveData<String>()
 val progressBar = MutableLiveData<Boolean>()
 var mealCategoryResponse = MutableLiveData<MealCategoryResponse?>()
 var drinksCategoryResponse = MutableLiveData<DrinkCategoryResponse?>()
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
     fun getMealCategory() {
        viewModelScope.launch {
            showProgressBar(true)
            dataRepository.getMealCategory()
                .let { response ->
                    showProgressBar(false)

                    when (response) {
                        is ResultWrapper.Success -> {
                         mealCategoryResponse .value = response.value
                        }
                        else -> handleErrorType(response)
                    }
                }
        }
    }
   fun getDrinksCategory() {
        viewModelScope.launch {
            showProgressBar(true)
            dataRepository.getDrinksCategory()
                .let { response ->
                    showProgressBar(false)

                    when (response) {
                        is ResultWrapper.Success -> {
                         drinksCategoryResponse .value = response.value
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