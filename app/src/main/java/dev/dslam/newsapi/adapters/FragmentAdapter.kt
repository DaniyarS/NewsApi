package dev.dslam.newsapi.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import dev.dslam.newsapi.Constants
import dev.dslam.newsapi.fragments.EverythingFragment
import dev.dslam.newsapi.fragments.HeadlinesFragment

class FragmentAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = mutableListOf<Fragment>()

    init {
        fragments.add(HeadlinesFragment())
        fragments.add(EverythingFragment())
    }

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> {
                return Constants.TOP_HEADLINES
            }
            1 -> {
                return Constants.EVERYTHING
            }
        }

        return position.toString()
    }

}