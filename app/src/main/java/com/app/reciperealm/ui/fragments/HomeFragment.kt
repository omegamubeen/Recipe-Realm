package com.app.reciperealm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.reciperealm.R
import com.app.reciperealm.adapters.BreakfastAdapter
import com.app.reciperealm.adapters.CategoryAdapter
import com.app.reciperealm.adapters.TrendingAdapter
import com.app.reciperealm.databinding.FragmentHomeBinding
import com.app.reciperealm.extensions.setGridLayout
import com.app.reciperealm.extensions.setHorizontalLayout
import com.app.reciperealm.extensions.setVerticalLayout
import com.app.reciperealm.models.remote.AllCategoryResponse
import com.app.reciperealm.models.remote.RandomRecipeResponse
import com.app.reciperealm.models.remote.RecipeByCategoryResponse
import com.app.reciperealm.network.Status
import com.app.reciperealm.utils.LoaderUtility.hideLoader
import com.app.reciperealm.utils.LoaderUtility.showLoader
import com.app.reciperealm.viewmodels.AllCategoryViewModel
import com.app.reciperealm.viewmodels.RandomViewModel
import com.app.reciperealm.viewmodels.RecipeByCategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null

    private val randomViewModel: RandomViewModel by viewModel()
    private val trendingAdapter by lazy { TrendingAdapter(this::onRandomClick) }
    private val randomList: ArrayList<RandomRecipeResponse.Meal> = ArrayList()

    private val allCategoryViewModel: AllCategoryViewModel by viewModel()
    private val breakfastAdapter by lazy { BreakfastAdapter(this::onRecipeClick) }
    private val breakfastList: ArrayList<AllCategoryResponse.Meal> = ArrayList()

    private val recipeCategoryViewModel: RecipeByCategoryViewModel by viewModel()
    private val recipeByCategoryAdapter by lazy { CategoryAdapter(this::onRecipeDetailClick) }
    private val categoryList: ArrayList<RecipeByCategoryResponse.Meal> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        return binding?.root
    }

    private fun onRecipeClick(item: AllCategoryResponse.Meal) {
        getRecipesCategory(item.strCategory)
    }

    private fun onRandomClick(item: RandomRecipeResponse.Meal) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMakeRecipeFragment(item.idMeal))
        hideLoader()
    }

    private fun onRecipeDetailClick(item: RecipeByCategoryResponse.Meal) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMakeRecipeFragment(item.idMeal))
        hideLoader()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRandomRecipe()
        getBreakfastRecipe("list")

    }

    private fun getRandomRecipe() {
        randomViewModel.getRandomRecipes().observe(viewLifecycleOwner) { response ->
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
                    randomList.clear()
                    randomList.addAll(it?.toCollection(ArrayList())?.reversed() ?: ArrayList())
                    binding!!.rcvTrending.setHorizontalLayout()
                    binding!!.rcvTrending.adapter = trendingAdapter.apply { refresh(randomList) }

                }
            }
        }
    }

    private fun getBreakfastRecipe(category: String) {
        allCategoryViewModel.getAllCategory(category).observe(viewLifecycleOwner) { response ->
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
                    breakfastList.addAll(it?.toCollection(ArrayList())?.reversed() ?: ArrayList())
                    binding!!.rcvBreakfast.setHorizontalLayout()
                    binding!!.rcvBreakfast.adapter =
                        breakfastAdapter.apply { refresh(breakfastList) }
                }
            }
        }
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