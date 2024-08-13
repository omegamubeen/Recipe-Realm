package com.app.reciperealm.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.reciperealm.databinding.ItemRecentBinding
import com.app.reciperealm.ui.model.RecentModel
import com.app.reciperealm.ui.utils.loadImageFromUrl

class RecentRecipeAdapter(private val recentList: MutableList<RecentModel>) :
    RecyclerView.Adapter<RecentRecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = recentList[position]

        with(holder.binding) {
            name.text = model.userName
            title.text = model.title
            ivImage.loadImageFromUrl(model.image)
        }
    }

    override fun getItemCount(): Int = recentList.size

    inner class ViewHolder(val binding: ItemRecentBinding) : RecyclerView.ViewHolder(binding.root)

}