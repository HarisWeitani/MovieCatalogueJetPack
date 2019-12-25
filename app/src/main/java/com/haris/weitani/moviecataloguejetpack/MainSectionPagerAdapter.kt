package com.haris.weitani.moviecataloguejetpack

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.haris.weitani.moviecataloguejetpack.common.MovieListFragment
import com.haris.weitani.moviecataloguejetpack.common.TvShowListFragment

class MainSectionPagerAdapter(private val context: Context, fm : FragmentManager) : FragmentPagerAdapter( fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{

    @StringRes
    private val TAB_TITLES = intArrayOf(
        R.string.tab1_title,
        R.string.tab2_title)

    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(TAB_TITLES[position])

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        when ( position ){
            0 ->{
                fragment =
                    MovieListFragment()
            }
            1 ->{
                fragment =
                    TvShowListFragment()
            }
        }
        return fragment as Fragment
    }

    override fun getCount(): Int = 2

}