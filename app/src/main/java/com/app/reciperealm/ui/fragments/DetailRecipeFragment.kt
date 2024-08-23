package com.app.reciperealm.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.reciperealm.R
import com.app.reciperealm.adapters.DetailAdapter
import com.app.reciperealm.databinding.FragmentDetailRecipeBinding
import com.app.reciperealm.extensions.hide
import com.app.reciperealm.extensions.setVerticalLayout
import com.app.reciperealm.extensions.show
import com.app.reciperealm.models.remote.AllDetailRecipeResponse
import com.app.reciperealm.network.Status
import com.app.reciperealm.utils.LoaderUtility.hideLoader
import com.app.reciperealm.utils.LoaderUtility.showLoader
import com.app.reciperealm.utils.loadImageFromUrl
import com.app.reciperealm.viewmodels.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailRecipeFragment : Fragment(R.layout.fragment_detail_recipe) {

    private var binding: FragmentDetailRecipeBinding? = null
    private val detailAdapter by lazy { DetailAdapter(this::onRecipeDetailClick) }
    private val detailList: ArrayList<AllDetailRecipeResponse.Meal> = ArrayList()
    private val detailViewModel: DetailViewModel by viewModel()
    private val args by navArgs<DetailRecipeFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun onRecipeDetailClick(item: AllDetailRecipeResponse.Meal) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        getDetailRecipe(args.id)
    }

    private fun openLinkYoutube(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }

    private fun openLinkInstagram(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }

    private fun getDetailRecipe(id: String) {
        detailViewModel.getRecipesDetail(id).observe(viewLifecycleOwner) { response ->
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
                    binding!!.tvFindBest.text = "How to make " + it?.first()?.strMeal
                    binding!!.tvCategory.text = "Category :  " + it?.first()?.strCategory
                    binding!!.tvLocation.text = it?.first()?.strArea
                    binding!!.ivVideo.loadImageFromUrl(it?.first()?.strMealThumb)
                    binding!!.cardView.setOnClickListener { it2 ->
                        openLinkYoutube(it?.first()?.strYoutube ?: "")
                    }
                    binding!!.btnInstagram.setOnClickListener { it2 ->
                        openLinkInstagram(it?.first()?.strSource ?: "")
                    }
                    if (it?.first()?.strSource.isNullOrEmpty()) {
                        binding!!.btnInstagram.hide()
                    }else {
                        binding!!.btnInstagram.show()
                    }
                    detailList.clear()
                    detailList.addAll(it?.toCollection(ArrayList())?.reversed() ?: ArrayList())
                    binding!!.rcvIngredients.setVerticalLayout()
                    binding!!.rcvIngredients.adapter = detailAdapter.apply { refresh(detailList) }
                }
            }
        }
    }

}