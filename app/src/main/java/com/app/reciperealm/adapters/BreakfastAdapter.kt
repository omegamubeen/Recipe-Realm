package com.app.reciperealm.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.reciperealm.R
import com.app.reciperealm.databinding.ItemPopularBinding
import com.app.reciperealm.models.remote.AllCategoryResponse
import com.app.reciperealm.ui.model.BreakfastModel
import com.app.reciperealm.ui.model.RecentModel
import kotlin.reflect.KFunction1

class BreakfastAdapter(
    val onClick: (AllCategoryResponse.Meal) -> Unit
) : RecyclerView.Adapter<BreakfastAdapter.ViewHolder>() {

    private val category: ArrayList<AllCategoryResponse.Meal> = ArrayList()
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val model = category[position]

        with(holder.binding) {
            tvBreakfast.text = model.strCategory

            if (position == selectedItemPosition) {
                root.setCardBackgroundColor(root.context.resources.getColor(R.color.red))
                tvBreakfast.setTextColor(root.context.resources.getColor(R.color.white))
            } else {
                root.setCardBackgroundColor(root.context.resources.getColor(R.color.white))
                tvBreakfast.setTextColor(root.context.resources.getColor(R.color.lightRed))
            }

            root.setOnClickListener {
                selectedItemPosition = position
                onClick(model)
                notifyDataSetChanged()
            }

        }
    }

    override fun getItemCount(): Int = category.size

    inner class ViewHolder(val binding: ItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(newList: ArrayList<AllCategoryResponse.Meal>) {
        category.clear()
        category.addAll(newList)
        notifyDataSetChanged()
    }
}