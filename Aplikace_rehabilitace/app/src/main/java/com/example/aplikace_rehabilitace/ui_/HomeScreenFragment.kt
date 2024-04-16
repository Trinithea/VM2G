package com.example.aplikace_rehabilitace.ui_

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentHomeScreenBinding
import com.example.aplikace_rehabilitace.databinding.FragmentWelcomeBinding
import com.google.android.material.tabs.TabLayout


class HomeScreenFragment : Fragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentHomeScreenBinding.bind(view)
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager
        adapter = ViewPagerAdapter(getParentFragmentManager() , lifecycle)

        viewPager.adapter = adapter
        tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null){
                    viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

    }

}