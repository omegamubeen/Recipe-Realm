package com.app.reciperealm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.reciperealm.R
import com.app.reciperealm.databinding.FragmentCreateRecipeBinding
import com.app.reciperealm.extensions.setVerticalLayout
import com.app.reciperealm.ui.adapters.IngredientsAdapter
import com.app.reciperealm.ui.model.CategoryModel

class CreateRecipeFragment : Fragment(R.layout.fragment_create_recipe) {

    private var binding: FragmentCreateRecipeBinding? = null
    private val ingredientsAdapter by lazy { IngredientsAdapter(categroyList) }
    private val categroyList: ArrayList<CategoryModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateRecipeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ingredients()

    }

    fun ingredients() {
        binding!!.rcvIngredients.apply {
            setVerticalLayout()
            adapter = ingredientsAdapter
        }
        val categroysList = mutableListOf(
            CategoryModel(
                "\uD83C\uDF63",
                "Bread",
                "200g",
            ),
            CategoryModel(
                "\uD83C\uDF63",
                "Bread",
                "200g",
            ),
            CategoryModel(
                "\uD83C\uDF63",
                "Bread",
                "200g",
            ),
            CategoryModel(
                "\uD83C\uDF63",
                "Bread",
                "200g",
            ),
            CategoryModel(
                "\uD83C\uDF63",
                "Bread",
                "200g",
            ),
        )
        binding!!.rcvIngredients.adapter = IngredientsAdapter(categroysList)
    }

}