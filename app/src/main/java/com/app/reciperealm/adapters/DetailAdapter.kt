package com.app.reciperealm.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.reciperealm.databinding.ItemIngredientsBinding
import com.app.reciperealm.model.CategoryModel
import com.app.reciperealm.models.remote.AllCategoryResponse
import com.app.reciperealm.models.remote.AllDetailRecipeResponse
import com.app.reciperealm.models.remote.RandomRecipeResponse
import com.app.reciperealm.models.remote.RecipeByCategoryResponse
import com.app.reciperealm.utils.loadImageFromUrl

class DetailAdapter(val onClick: (AllDetailRecipeResponse.Meal) -> Unit
) :
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private val detail: ArrayList<AllDetailRecipeResponse.Meal> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemIngredientsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = detail[position]

        with(holder.binding) {
            ivRecipe.loadImageFromUrl(model.strMealThumb)
            tvTitle.text = model.strIngredient1
            tvItems.text = model.strMeasure1

            root.setOnClickListener {
                onClick(model)
                notifyDataSetChanged()
            }

        }
    }

    override fun getItemCount(): Int = detail.size

    inner class ViewHolder(val binding: ItemIngredientsBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(newList: ArrayList<AllDetailRecipeResponse.Meal>) {
        detail.clear()
        detail.addAll(newList)
        notifyDataSetChanged()
    }
}