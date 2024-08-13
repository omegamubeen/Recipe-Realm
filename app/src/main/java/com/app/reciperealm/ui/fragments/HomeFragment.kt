package com.app.reciperealm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.app.reciperealm.R
import com.app.reciperealm.databinding.FragmentHomeBinding
import com.app.reciperealm.extensions.setHorizontalLayout
import com.app.reciperealm.ui.adapters.CategoryAdapter
import com.app.reciperealm.ui.adapters.CreatorsAdapter
import com.app.reciperealm.ui.adapters.RecentRecipeAdapter
import com.app.reciperealm.ui.adapters.TrendingAdapter
import com.app.reciperealm.ui.model.CategoryModel
import com.app.reciperealm.ui.model.CreatorsModel
import com.app.reciperealm.ui.model.RecentModel
import com.app.reciperealm.ui.model.TrendingModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null

    private val trendingAdapter by lazy { TrendingAdapter(list) }
    private val list: ArrayList<TrendingModel> = ArrayList()

    private val categoryAdapter by lazy { CategoryAdapter(categroyList) }
    private val categroyList: ArrayList<CategoryModel> = ArrayList()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trendingNow()

        popularRecipeImage()

        recentRecipe()

        creators()

    }

    fun trendingNow() {
        binding!!.rcvTrending.apply {
            setHorizontalLayout()
            adapter = trendingAdapter
        }
        val trendingList = mutableListOf(
            TrendingModel(
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
                "By Niki Samantha",
                "How to make french toast",
                "https://tse4.mm.bing.net/th?id=OIP.1czg1cpKTKn-Z_uQyfKEtAHaE8&pid=Api&P=0&h=220",
                "15:30"
            ),
            TrendingModel(
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
                "By Niki Samantha",
                "How to make french toast",
                "https://tse4.mm.bing.net/th?id=OIP.1czg1cpKTKn-Z_uQyfKEtAHaE8&pid=Api&P=0&h=220",
                "15:30"
            ),
            TrendingModel(
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
                "By Niki Samantha",
                "How to make french toast",
                "https://tse4.mm.bing.net/th?id=OIP.1czg1cpKTKn-Z_uQyfKEtAHaE8&pid=Api&P=0&h=220",
                "15:30"
            ),
            TrendingModel(
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
                "By Niki Samantha",
                "How to make french toast",
                "https://tse4.mm.bing.net/th?id=OIP.1czg1cpKTKn-Z_uQyfKEtAHaE8&pid=Api&P=0&h=220",
                "15:30"
            ),
            TrendingModel(
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
                "By Niki Samantha",
                "How to make french toast",
                "https://tse4.mm.bing.net/th?id=OIP.1czg1cpKTKn-Z_uQyfKEtAHaE8&pid=Api&P=0&h=220",
                "15:30"
            ),
        )
        binding!!.rcvTrending.adapter = TrendingAdapter(trendingList)
    }

    fun popularRecipeImage() {
        binding!!.rcvPopularRecipe.apply {
            setHorizontalLayout()
            adapter = categoryAdapter
        }
        val categroysList = mutableListOf(
            CategoryModel(
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
                "Indonesian chicken burger",
                "10:20",
            ),
            CategoryModel(
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
                "Indonesian chicken burger",
                "10:20",
            ),
            CategoryModel(
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
                "Indonesian chicken burger",
                "10:20",
            ),
            CategoryModel(
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
                "Indonesian chicken burger",
                "10:20",
            ),
            CategoryModel(
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
                "Indonesian chicken burger",
                "10:20",
            ),
            CategoryModel(
                "https://images.search.yahoo.com/images/view;_ylt=AwrijLJc_LRm8sgEEWWJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzM2ODI4ZDY5ZjEyYmEwZDdlYTU5N2ZlY2Y3MzhjMTQyBGdwb3MDMjkEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dfood%2Bcooking%2Brecipes%2Bpictures%26type%3DE210US91215G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D29&w=832&h=468&imgurl=ichef.bbci.co.uk%2Ffood%2Fic%2Ffood_16x9_832%2Frecipes%2Fhow_to_cook_courgette_23829_16x9.jpg&rurl=https%3A%2F%2Fwww.bbc.co.uk%2Ffood%2Frecipes%2Fhow_to_cook_courgette_23829&size=51.6KB&p=food+cooking+recipes+pictures&oid=36828d69f12ba0d7ea597fecf738c142&fr2=piv-web&fr=mcafee&tt=How+to+cook+courgettes+recipe+-+BBC+Food&b=0&ni=21&no=29&ts=&tab=organic&sigr=lr_fOIMRRHbb&sigb=WlkP9YVOVGKN&sigi=c_gNvDbjs2JH&sigt=UGD5h747ezqx&.crumb=h70Eg4xmuxF&fr=mcafee&fr2=piv-web&type=E210US91215G0",
                "Indonesian chicken burger",
                "10:20",
            ),
        )
        binding!!.rcvPopularRecipe.adapter = CategoryAdapter(categroysList)
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