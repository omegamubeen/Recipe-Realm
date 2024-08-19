package com.app.reciperealm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.reciperealm.R
import com.app.reciperealm.databinding.FragmentDetailRecipeBinding
import com.app.reciperealm.utils.LoaderUtility.hideLoader

class DetailRecipeFragment : Fragment(R.layout.fragment_detail_recipe) {

    private var binding: FragmentDetailRecipeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.ivBack.setOnClickListener{
            findNavController().popBackStack()
        }

        hideLoader()

    }
}