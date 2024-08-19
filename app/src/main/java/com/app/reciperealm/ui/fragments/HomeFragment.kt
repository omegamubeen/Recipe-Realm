package com.app.reciperealm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.reciperealm.R
import com.app.reciperealm.adapters.BreakfastAdapter
import com.app.reciperealm.adapters.CategoryAdapter
import com.app.reciperealm.adapters.TrendingAdapter
import com.app.reciperealm.databinding.FragmentHomeBinding
import com.app.reciperealm.extensions.setHorizontalLayout
import com.app.reciperealm.models.remote.AllCategoryResponse
import com.app.reciperealm.models.remote.RandomRecipeResponse
import com.app.reciperealm.models.remote.RecipeByCategoryResponse
import com.app.reciperealm.network.Status
import com.app.reciperealm.ui.adapters.CreatorsAdapter
import com.app.reciperealm.ui.adapters.RecentRecipeAdapter
import com.app.reciperealm.ui.model.CreatorsModel
import com.app.reciperealm.ui.model.RecentModel
import com.app.reciperealm.utils.LoaderUtility.hideLoader
import com.app.reciperealm.utils.LoaderUtility.showLoader
import com.app.reciperealm.viewmodels.AllCategoryViewModel
import com.app.reciperealm.viewmodels.RandomViewModel
import com.app.reciperealm.viewmodels.RecipeByCategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null

    private val randomViewModel: RandomViewModel by viewModel()
    private val trendingAdapter by lazy { TrendingAdapter() }
    private val randomList: ArrayList<RandomRecipeResponse.Meal> = ArrayList()

    private val allCategoryViewModel: AllCategoryViewModel by viewModel()
    private val breakfastAdapter by lazy { BreakfastAdapter(this::onRecipeClick) }
    private val breakfastList: ArrayList<AllCategoryResponse.Meal> = ArrayList()

    private val recipeCategoryViewModel: RecipeByCategoryViewModel by viewModel()
    private val recipeByCategoryAdapter by lazy { CategoryAdapter(this::onRecipeDetailClick) }
    private val categoryList: ArrayList<RecipeByCategoryResponse.Meal> = ArrayList()

    private val recentRecipeAdapter by lazy { RecentRecipeAdapter(recentsList) }
    private val recentsList: ArrayList<RecentModel> = ArrayList()

    private val creatorsAdapter by lazy { CreatorsAdapter(creatorsList) }
    private val creatorsList: ArrayList<CreatorsModel> = ArrayList()

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

    private fun onRecipeDetailClick(item: RecipeByCategoryResponse.Meal) {
        getRecipesCategory(item.idMeal)
        findNavController().navigate(R.id.action_homeFragment_to_makeRecipeFragment)
        hideLoader()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRandomRecipe()
        getBreakfastRecipe("list")

        recentRecipe()
        creators()
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
                        binding!!.rcvPopularRecipe.setHorizontalLayout()
                        binding!!.rcvPopularRecipe.adapter =
                            recipeByCategoryAdapter.apply { refresh(categoryList) }
                    }
                }
            }
    }

    fun recentRecipe() {
        binding!!.rcvRecentRecipe.apply {
            setHorizontalLayout()
            adapter = recentRecipeAdapter
        }
        val recentList = mutableListOf(
            RecentModel(
                "By Adrianna Curl",
                "Indonesian chicken burger",
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
            ),
            RecentModel(
                "By Adrianna Curl",
                "Indonesian chicken burger",
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
            ),
            RecentModel(
                "By Adrianna Curl",
                "Indonesian chicken burger",
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
            ),
            RecentModel(
                "By Adrianna Curl",
                "Indonesian chicken burger",
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
            ),
            RecentModel(
                "By Adrianna Curl",
                "Indonesian chicken burger",
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
            ),

            )
        binding!!.rcvRecentRecipe.adapter = RecentRecipeAdapter(recentList)
    }

    fun creators() {
        binding!!.rcvCreators.apply {
            setHorizontalLayout()
            adapter = creatorsAdapter
        }
        val creatorList = mutableListOf(
            CreatorsModel(
                "By Niki Samantha",
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
            ),
            CreatorsModel(
                "By Niki Samantha",
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
            ),
            CreatorsModel(
                "By Niki Samantha",
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
            ),
            CreatorsModel(
                "By Niki Samantha",
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
            ),
        )
        binding!!.rcvCreators.adapter = CreatorsAdapter(creatorList)
    }

}