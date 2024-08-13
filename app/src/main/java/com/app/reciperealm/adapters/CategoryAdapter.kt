package com.app.reciperealm.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.reciperealm.databinding.ItemPopularRecipeBinding
import com.app.reciperealm.ui.model.CategoryModel
import com.app.reciperealm.ui.utils.loadImageFromUrl

class CategoryAdapter(private val creatorsList: MutableList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPopularRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = creatorsList[position]

        with(holder.binding) {
            ivRecipe.loadImageFromUrl(model.image)
            tvRecipeName.text = model.title
            tvTime.text = model.time
        }
    }

    override fun getItemCount(): Int = creatorsList.size

    inner class ViewHolder(val binding: ItemPopularRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

}