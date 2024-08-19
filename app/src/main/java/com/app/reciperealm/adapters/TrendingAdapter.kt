package com.app.reciperealm.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.reciperealm.databinding.ItemTrendingBinding
import com.app.reciperealm.models.remote.RandomRecipeResponse
import com.app.reciperealm.utils.loadImageFromUrl

class TrendingAdapter(

) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    private val random: ArrayList<RandomRecipeResponse.Meal> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = random[position]

        with(holder.binding) {
            ivVideo.loadImageFromUrl(model.strMealThumb)
            tvVideoTitle.text = model.strMeal
        }
    }

    override fun getItemCount(): Int = random.size

    inner class ViewHolder(val binding: ItemTrendingBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(newList: ArrayList<RandomRecipeResponse.Meal>) {
        random.clear()
        random.addAll(newList)
        notifyDataSetChanged()
    }

}