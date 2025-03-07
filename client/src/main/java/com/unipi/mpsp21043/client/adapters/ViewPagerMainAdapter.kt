package com.unipi.mpsp21043.client.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.unipi.mpsp21043.client.ui.fragments.FavoritesFragment
import com.unipi.mpsp21043.client.ui.fragments.HomeFragment
import com.unipi.mpsp21043.client.ui.fragments.MyAccountFragment

class ViewPagerMainAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    private val fragmentsList:ArrayList<Fragment> = arrayListOf(
        HomeFragment(),
        FavoritesFragment(),
        MyAccountFragment()
    )

    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position]
    }
}
