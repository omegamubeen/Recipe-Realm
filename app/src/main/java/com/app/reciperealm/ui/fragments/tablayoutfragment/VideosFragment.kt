package com.app.reciperealm.ui.fragments.tablayoutfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.reciperealm.R
import com.app.reciperealm.adapters.TrendingAdapter
import com.app.reciperealm.databinding.FragmentVideosBinding
import com.app.reciperealm.extensions.setHorizontalLayout
import com.app.reciperealm.models.remote.RandomRecipeResponse
import com.app.reciperealm.network.Status
import com.app.reciperealm.utils.LoaderUtility
import com.app.reciperealm.utils.LoaderUtility.showLoader
import com.app.reciperealm.viewmodels.RandomViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideosFragment : Fragment(R.layout.fragment_videos) {

    private var binding: FragmentVideosBinding? = null
    private val randomViewModel: RandomViewModel by viewModel()
    private val trendingAdapter by lazy { TrendingAdapter() }
    private val randomList: ArrayList<RandomRecipeResponse.Meal> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRandomRecipe()

    }

    private fun getRandomRecipe() {
        randomViewModel.getRandomRecipes().observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.ERROR -> {
                    LoaderUtility.hideLoader()
                }

                Status.LOADING -> {
                    showLoader()
                }

                Status.SUCCESS -> {
                    LoaderUtility.hideLoader()
                    val it = response.data?.meals
                    randomList.clear()
                    randomList.addAll(it?.toCollection(ArrayList())?.reversed() ?: ArrayList())
                    binding!!.rcvTrending.apply {
                        setHorizontalLayout()
                        adapter = trendingAdapter
                    }
                    binding!!.rcvTrending.setHorizontalLayout()
                    binding!!.rcvTrending.adapter = trendingAdapter.apply { refresh(randomList) }

                }
            }
        }
    }

}