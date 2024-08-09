package com.app.reciperealm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.reciperealm.R
import com.app.reciperealm.databinding.FragmentCreateRecipeBinding
import com.app.reciperealm.databinding.FragmentMakeRecipeBinding

class MakeRecipeFragment : Fragment(R.layout.fragment_make_recipe) {

    private var binding: FragmentMakeRecipeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMakeRecipeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}