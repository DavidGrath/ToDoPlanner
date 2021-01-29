package com.example.todoplanner.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todoplanner.ui.fragments.DailyGoalsFragment
import com.example.todoplanner.ui.fragments.MonthlyGoalsFragment
import com.example.todoplanner.ui.fragments.WeeklyGoalsFragment

class GoalsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        val dailyFrag = DailyGoalsFragment()
        val weeklyFrag = WeeklyGoalsFragment()
        val monthlyFrag = MonthlyGoalsFragment()
        return when (position) {
            1 -> dailyFrag
            2 -> weeklyFrag
            3 -> monthlyFrag
            else -> dailyFrag
        }
    }
}