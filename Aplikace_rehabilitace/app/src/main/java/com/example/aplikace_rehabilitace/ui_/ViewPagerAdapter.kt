package com.example.aplikace_rehabilitace.ui_

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aplikace_rehabilitace.ui_.account.TherapyHomeScreenFragment

class ViewPagerAdapter (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle){

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return if (position==0)
            TherapyHomeScreenFragment()
        else if  (position == 1)
            ConsultationFragment()
        else
            LiteratureFragment()
    }


}