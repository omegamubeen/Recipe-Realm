package com.app.reciperealm.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.reciperealm.ui.fragments.ProfileFragment
import com.app.reciperealm.ui.fragments.tablayoutfragment.RecipeFragment
import com.app.reciperealm.ui.fragments.tablayoutfragment.VideosFragment

class ViewPagerAdapter(fragmentActivity: ProfileFragment) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VideosFragment()
            1 -> RecipeFragment()
            else -> VideosFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
