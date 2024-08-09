package com.app.reciperealm.ui.fragments.tablayoutfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.reciperealm.R
import com.app.reciperealm.databinding.FragmentVideosBinding
import com.app.reciperealm.extensions.setVerticalLayout
import com.app.reciperealm.ui.adapters.TrendingAdapter
import com.app.reciperealm.ui.model.TrendingModel

class VideosFragment : Fragment(R.layout.fragment_videos) {

    private var binding: FragmentVideosBinding? = null
    private val trendingAdapter by lazy { TrendingAdapter(list) }
    private val list: ArrayList<TrendingModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.rcvTrending.apply {
            setVerticalLayout()
            adapter = trendingAdapter
        }
        val likesList = mutableListOf(
            TrendingModel(
                "https://tse4.mm.bing.net/th?id=OIP.9il3mslQq4vwUr0krbmBlwHaGg&pid=Api&P=0&h=220",
                "By Niki Samantha",
                "How to make french toast",
                "https://tse4.mm.bing.net/th?id=OIP.1czg1cpKTKn-Z_uQyfKEtAHaE8&pid=Api&P=0&h=220",
                "15:30"
            ),
            TrendingModel(
                "https://tse4.mm.bing.net/th?id=OIP.9il3mslQq4vwUr0krbmBlwHaGg&pid=Api&P=0&h=220",
                "By Niki Samantha",
                "How to make french toast",
                "https://tse4.mm.bing.net/th?id=OIP.1czg1cpKTKn-Z_uQyfKEtAHaE8&pid=Api&P=0&h=220",
                "15:30"
            ),
            TrendingModel(
                "https://tse4.mm.bing.net/th?id=OIP.9il3mslQq4vwUr0krbmBlwHaGg&pid=Api&P=0&h=220",
                "By Niki Samantha",
                "How to make french toast",
                "https://tse4.mm.bing.net/th?id=OIP.1czg1cpKTKn-Z_uQyfKEtAHaE8&pid=Api&P=0&h=220",
                "15:30"
            ),
            TrendingModel(
                "https://tse4.mm.bing.net/th?id=OIP.9il3mslQq4vwUr0krbmBlwHaGg&pid=Api&P=0&h=220",
                "By Niki Samantha",
                "How to make french toast",
                "https://tse4.mm.bing.net/th?id=OIP.1czg1cpKTKn-Z_uQyfKEtAHaE8&pid=Api&P=0&h=220",
                "15:30"
            ),
            TrendingModel(
                "https://tse4.mm.bing.net/th?id=OIP.9il3mslQq4vwUr0krbmBlwHaGg&pid=Api&P=0&h=220",
                "By Niki Samantha",
                "How to make french toast",
                "https://tse4.mm.bing.net/th?id=OIP.1czg1cpKTKn-Z_uQyfKEtAHaE8&pid=Api&P=0&h=220",
                "15:30"
            ),
        )
        binding!!.rcvTrending.adapter = TrendingAdapter(likesList)

    }
}