package com.app.reciperealm.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.reciperealm.databinding.ItemTrendingBinding
import com.app.reciperealm.ui.model.TrendingModel
import com.app.reciperealm.ui.utils.loadImageFromUrl

class TrendingAdapter(private val list: MutableList<TrendingModel>) :
    RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        with(holder.binding) {
            ivVideo.loadImageFromUrl(model.video)
            tvUserName.text = model.userName
            tvVideoTitle.text = model.title
            ivPerson.loadImageFromUrl(model.image)
            tvDruation.text = model.time
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemTrendingBinding) : RecyclerView.ViewHolder(binding.root)

}