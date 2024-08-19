package com.app.reciperealm.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.reciperealm.databinding.ItemNotificationBinding
import com.app.reciperealm.ui.model.MessageModel

class NotificationAdapter(private val MessageList: MutableList<MessageModel>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = MessageList[position]

        with(holder.binding) {
            tvTitle.text = model.title
            tvMassage.text = model.message
        }
    }

    override fun getItemCount(): Int = MessageList.size

    inner class ViewHolder(val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root)

}