package com.app.reciperealm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.reciperealm.R
import com.app.reciperealm.databinding.FragmentNotificationBinding
import com.app.reciperealm.extensions.setVerticalLayout
import com.app.reciperealm.ui.adapters.NotificationAdapter
import com.app.reciperealm.ui.model.MessageModel

class NotificationFragment : Fragment(R.layout.fragment_notification) {

    private var binding: FragmentNotificationBinding? = null

    private val notificationAdapter by lazy { NotificationAdapter(messagesList) }
    private val messagesList: ArrayList<MessageModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notification()

    }

    fun notification() {
        binding!!.rcvNotification.apply {
            setVerticalLayout()
            adapter = notificationAdapter
        }
        val messageList = mutableListOf(
            MessageModel(
                "New recipe!",
                "Far far away, behind the word mountains, far from the countries.",
            ),
            MessageModel(
                "Don’t forget to try your saved recipe",
                "Far far away, behind the word mountains, far from the countries.",
            ),
            MessageModel(
                "New recipe!",
                "Far far away, behind the word mountains, far from the countries.",
            ),
            MessageModel(
                "New recipe!",
                "Far far away, behind the word mountains, far from the countries.",
            ),
            MessageModel(
                "New recipe!",
                "Far far away, behind the word mountains, far from the countries.",
            ),
            MessageModel(
                "New recipe!",
                "Far far away, behind the word mountains, far from the countries.",
            ),
            MessageModel(
                "New recipe!",
                "Far far away, behind the word mountains, far from the countries.",
            ),
            MessageModel(
                "New recipe!",
                "Far far away, behind the word mountains, far from the countries.",
            ),
        )
        binding!!.rcvNotification.adapter = NotificationAdapter(messageList)
    }

}