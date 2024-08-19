package com.app.reciperealm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.reciperealm.R
import com.app.reciperealm.adapters.CategoryAdapter
import com.app.reciperealm.databinding.FragmentFavoriteBinding
import com.app.reciperealm.extensions.setGridLayout
import com.app.reciperealm.models.remote.RecipeByCategoryResponse
import com.app.reciperealm.network.Status
import com.app.reciperealm.utils.LoaderUtility
import com.app.reciperealm.utils.LoaderUtility.hideLoader
import com.app.reciperealm.utils.LoaderUtility.showLoader
import com.app.reciperealm.viewmodels.RecipeByCategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var binding: FragmentFavoriteBinding? = null
    private val recipeCategoryViewModel: RecipeByCategoryViewModel by viewModel()
    private val recipeByCategoryAdapter by lazy { CategoryAdapter(this::onRecipeDetailClick) }
    private val categoryList: ArrayList<RecipeByCategoryResponse.Meal> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun onRecipeDetailClick(item: RecipeByCategoryResponse.Meal) {
        findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToMakeRecipeFragment(item.idMeal))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRecipesCategory("Vegetarian")

    }

    private fun getRecipesCategory(category: String) {
        recipeCategoryViewModel.getRecipesCategory(category)
            .observe(viewLifecycleOwner) { response ->
                when (response.status) {
                    Status.ERROR -> {
                        hideLoader()
                    }

                    Status.LOADING -> {
                        showLoader()
                    }

                    Status.SUCCESS -> {
                        hideLoader()
                        val it = response.data?.meals
                        categoryList.clear()
                        categoryList.addAll(
                            it?.toCollection(ArrayList())?.reversed() ?: ArrayList()
                        )
                        binding!!.rcvPopularRecipe.setGridLayout(2)
                        binding!!.rcvPopularRecipe.adapter =
                            recipeByCategoryAdapter.apply { refresh(categoryList) }
                    }
                }
            }
    }

}