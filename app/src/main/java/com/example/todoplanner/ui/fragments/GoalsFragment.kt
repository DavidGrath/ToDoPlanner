package com.example.todoplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoplanner.databinding.FragmentGoalsBinding
import com.example.todoplanner.ui.GoalsViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class GoalsFragment : Fragment() {
    lateinit var binding: FragmentGoalsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGoalsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Goals"
        binding.goalsViewpager.requestDisallowInterceptTouchEvent(true)
        binding.goalsViewpager.adapter = GoalsViewPagerAdapter(this)
        val titles = arrayOf<String>("Daily", "Weekly", "Monthly")
        TabLayoutMediator(binding.tabs, binding.goalsViewpager) { tab, pos ->
            tab.text = titles[pos]
        }.attach()
    }
}