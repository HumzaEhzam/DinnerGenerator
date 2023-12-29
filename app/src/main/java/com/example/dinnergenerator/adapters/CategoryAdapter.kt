package com.example.dinnergenerator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinnergenerator.databinding.CategoryCardItemBinding
import com.example.dinnergenerator.interfaces.CategoryClickListener
import com.example.dinnergenerator.models.netwrok.meals.MealCategory
import com.example.dinnergenerator.view.holders.CategoryViewHolder

import kotlin.collections.ArrayList

class CategoryAdapter(val listener: CategoryClickListener) :
    RecyclerView.Adapter<CategoryViewHolder>() {


    val originalList = ArrayList<MealCategory>()
    var lastCheckItem = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            CategoryCardItemBinding.inflate(LayoutInflater.from(parent.context))
        )


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val model = originalList[position]
        holder.binding.model = model

        holder.binding.btnSelect.isChecked = model.isChecked
        holder.binding.btnSelect.setOnClickListener {
            val isChecked = holder.binding.btnSelect.isChecked
            if (isChecked) {
                listener.onCategoryAdd(model, position)
                if (lastCheckItem != -1) {
                    originalList[lastCheckItem].isChecked = false
                    notifyItemChanged(lastCheckItem)
                }
                lastCheckItem = position
            } else {
                listener.onCategoryRemoved()
            }
            originalList[position].isChecked = isChecked
        }

    }


    fun setOriginalList(list: ArrayList<MealCategory>) {
        originalList.clear()
        originalList.addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return originalList.size
    }
}