package com.app.reciperealm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.reciperealm.R
import com.app.reciperealm.adapters.SearchAdapter
import com.app.reciperealm.databinding.FragmentSearchResultBinding
import com.app.reciperealm.extensions.setGridLayout
import com.app.reciperealm.models.remote.SearchResponse
import com.app.reciperealm.network.Status
import com.app.reciperealm.utils.LoaderUtility
import com.app.reciperealm.utils.LoaderUtility.showLoader
import com.app.reciperealm.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultFragment : Fragment(R.layout.fragment_search_result) {

    private var binding: FragmentSearchResultBinding? = null
    private val searchViewModel: SearchViewModel by viewModel()
    private val searchAdapter by lazy { SearchAdapter(this::onSearchClick) }
    private val searchList: ArrayList<SearchResponse.Meal> = ArrayList()
    private val args by navArgs<SearchResultFragmentArgs>()

    private fun onSearchClick(item: SearchResponse.Meal) {
        findNavController().navigate(
            SearchResultFragmentDirections.actionSearchResultFragmentToDetailRecipeFragment(
                item.idMeal
            )
        )
        LoaderUtility.hideLoader()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        getRecipesCategory(args.search)

    }

    private fun getRecipesCategory(search: String) {
        searchViewModel.getSearchRecipe(search)
            .observe(viewLifecycleOwner) { response ->
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
                        searchList.clear()
                        searchList.addAll(
                            it?.toCollection(ArrayList())?.reversed() ?: ArrayList()
                        )
                        binding!!.rcvSearchResult.setGridLayout(2)
                        binding!!.rcvSearchResult.adapter =
                            searchAdapter.apply { refresh(searchList) }
                    }
                }
            }
    }

}