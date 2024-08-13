package com.app.reciperealm.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.reciperealm.databinding.ItemCreatorsBinding
import com.app.reciperealm.ui.model.CreatorsModel
import com.app.reciperealm.ui.utils.loadImageFromUrl

class CreatorsAdapter(private val creatorsList: MutableList<CreatorsModel>) :
    RecyclerView.Adapter<CreatorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCreatorsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = creatorsList[position]

        with(holder.binding) {
            tvName.text = model.userName
            ivImage.loadImageFromUrl(model.image)
        }
    }

    override fun getItemCount(): Int = creatorsList.size

    inner class ViewHolder(val binding: ItemCreatorsBinding) : RecyclerView.ViewHolder(binding.root)

}