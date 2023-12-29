package com.example.dinnergenerator.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dinnergenerator.adapters.CategoryAdapter
import com.example.dinnergenerator.databinding.FragmentCatergoryBinding
import com.example.dinnergenerator.interfaces.CategoryClickListener
import com.example.dinnergenerator.models.netwrok.drinks.DrinkCategoryResponse
import com.example.dinnergenerator.models.netwrok.meals.MealCategory
import com.example.dinnergenerator.utils.LoadingDialog
import com.example.dinnergenerator.utils.showSnackBar
import com.example.dinnergenerator.view.models.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : Fragment(), CategoryClickListener {

    lateinit var adapter: CategoryAdapter
    lateinit var binding: FragmentCatergoryBinding
    private val viewModel: CategoryViewModel by viewModels()
    val loaderDialog by lazy { LoadingDialog(requireActivity()) }
    var mealCategory: MealCategory? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCatergoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        setUpListeners()
        btnListeners()
    }

    private fun btnListeners() {

        binding.btnGenerateMeal.setOnClickListener {
            val drinkCategory = binding.atvDrinks.text.toString()
            if (mealCategory != null && drinkCategory.isEmpty()
                    .not() && drinkCategory != "Select Drinks"
            ) {
                findNavController().navigate(
                    CategoryFragmentDirections.categoryFragmentToDinnerFragment(
                        mealCategory!!.strCategory, drinkCategory
                    )
                )

            } else {
                showSnackBar("Select both meal and drink")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMealCategory()
        viewModel.getDrinksCategory()
    }

    private fun setUpListeners() {

        with(viewModel)
        {
            mealCategoryResponse.observe(viewLifecycleOwner) {
                it?.let { it1 -> adapter.setOriginalList(it1.categories) }
            }

            drinksCategoryResponse.observe(viewLifecycleOwner) {
                it?.let { it1 -> setupDrinksAdapter(it1) }
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

    private fun setupDrinksAdapter(drinkCategoryResponse: DrinkCategoryResponse) {
        val list = arrayListOf<String>()
        drinkCategoryResponse.drinks.forEach {
            list.add(it.strCategory)
        }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            list
        )

        binding.atvDrinks.setAdapter(adapter)
    }

    private fun setUpAdapter() {

        adapter = CategoryAdapter(this)
        binding.rvCategory.adapter = adapter

    }

    override fun onCategoryAdd(mealCategory: MealCategory, position: Int) {
        this.mealCategory = mealCategory
    }

    override fun onCategoryRemoved() {
        mealCategory = null
    }

}