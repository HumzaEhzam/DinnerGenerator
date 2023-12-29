package com.example.dinnergenerator.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.dinnergenerator.adapters.CategoryAdapter
import com.example.dinnergenerator.databinding.FragmentCatergoryBinding
import com.example.dinnergenerator.databinding.FragmentDinnerBinding
import com.example.dinnergenerator.interfaces.CategoryClickListener
import com.example.dinnergenerator.models.netwrok.drinks.Drink
import com.example.dinnergenerator.models.netwrok.drinks.DrinkCategoryResponse
import com.example.dinnergenerator.models.netwrok.meals.Meal
import com.example.dinnergenerator.models.netwrok.meals.MealCategory
import com.example.dinnergenerator.utils.LoadingDialog
import com.example.dinnergenerator.utils.showSnackBar
import com.example.dinnergenerator.view.models.CategoryViewModel
import com.example.dinnergenerator.view.models.DinnerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random


@AndroidEntryPoint
class DinnerFragment : Fragment() {

    lateinit var binding: FragmentDinnerBinding
    private val viewModel: DinnerViewModel by viewModels()
    val loaderDialog by lazy { LoadingDialog(requireActivity()) }
    private val mealCategory by lazy { arguments?.let { DinnerFragmentArgs.fromBundle(it).categoryMeal } }
    private val drinkCategory by lazy { arguments?.let { DinnerFragmentArgs.fromBundle(it).categoryDrink } }
    private var isMealApiSuccessful = false
    private var isDrinkApiSuccessful = false
    var meals = listOf<Meal>()
    var drinks = listOf<Drink>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDinnerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
        btnListeners()
    }

    private fun btnListeners() {

        binding.btnGenerateDinner.setOnClickListener {
            generateDinner()
        }
    }

    override fun onResume() {
        super.onResume()

    }

    private fun setUpListeners() {

        with(viewModel)
        {
            mealCategory?.let { getMealByCategory(it) }
            drinkCategory?.let { getDrinkByCategory(it) }

            drinksByCategoryResponse.observe(viewLifecycleOwner) {

                isDrinkApiSuccessful = true
                if (it != null) {
                    drinks = it.drinks
                }
                if (isMealApiSuccessful) {
                    generateDinner()
                }

            }

            mealByCategoryResponse.observe(viewLifecycleOwner) {
                isMealApiSuccessful = true
                if (it != null) {
                    meals = it.meals
                }
                if (isDrinkApiSuccessful) {
                    generateDinner()
                }
            }

            mealByIDResponse.observe(viewLifecycleOwner) {
                val model = it?.meals?.get(0)
                binding.meal = model
            }

            drinksByIDResponse.observe(viewLifecycleOwner) {
                val model = it?.drinks?.get(0)
                binding.drink = model
            }

            progressBar.observe(viewLifecycleOwner) {
                if (it) loaderDialog.show()
                else loaderDialog.dismiss()
            }

            snakBarMessage.observe(viewLifecycleOwner) {
                showSnackBar(it)
            }
        }
    }

    private fun generateDinner() {

        val sizeOfMeals = meals.size
        val sizeOfDrinks = drinks.size
        val randomMealNumber = Random.nextInt(0, sizeOfMeals)
        val randomDrinkNumber = Random.nextInt(0, sizeOfDrinks)

        val drink = drinks[randomDrinkNumber]
        val meal = meals[randomMealNumber]

        viewModel.getDrinkById(drink.idDrink)
        viewModel.getMealById(meal.idMeal)


    }


}