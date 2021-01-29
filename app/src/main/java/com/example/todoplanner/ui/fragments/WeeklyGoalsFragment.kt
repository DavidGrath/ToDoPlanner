package com.example.todoplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoplanner.databinding.FragmentWeeklyGoalsBinding

class WeeklyGoalsFragment : Fragment() {
    lateinit var binding: FragmentWeeklyGoalsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeeklyGoalsBinding.inflate(inflater)
        return binding.root
    }
}