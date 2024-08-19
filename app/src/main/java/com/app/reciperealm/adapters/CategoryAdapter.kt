package com.app.reciperealm.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.reciperealm.databinding.ItemPopularRecipeBinding
import com.app.reciperealm.models.remote.AllCategoryResponse
import com.app.reciperealm.models.remote.RecipeByCategoryResponse
import com.app.reciperealm.utils.loadImageFromUrl

class CategoryAdapter(val onClick: (RecipeByCategoryResponse.Meal) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val category: ArrayList<RecipeByCategoryResponse.Meal> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPopularRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = category[position]

        with(holder.binding) {
            ivRecipe.loadImageFromUrl(model.strMealThumb)
            tvRecipeName.text = model.strMeal

            root.setOnClickListener {
                onClick(model)
                notifyDataSetChanged()
            }

        }

    }

    override fun getItemCount(): Int = category.size

    inner class ViewHolder(val binding: ItemPopularRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(newList: ArrayList<RecipeByCategoryResponse.Meal>) {
        category.clear()
        category.addAll(newList)
        notifyDataSetChanged()
    }

}