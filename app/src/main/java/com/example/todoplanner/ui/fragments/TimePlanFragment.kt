package com.example.todoplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoplanner.databinding.FragmentTimePlanBinding
import com.example.todoplanner.ui.adapters.TasksRecyclerAdapter
import com.example.todoplanner.ui.viewmodels.TimePlanViewModel

class TimePlanFragment : Fragment(), TasksRecyclerAdapter.AddButtonListener {

    lateinit var binding: FragmentTimePlanBinding
    lateinit var viewModel: TimePlanViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTimePlanBinding.inflate(inflater)
        return binding.root
    }


    //TODO Change all titles from onAttach instead of onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Time Plan"

//        viewModel = ViewModelProvider(this,
//                TimePlanViewModelFactory(requireContext().applicationContext, )).get(TimePlanViewModel::class.java)
//        val timePlan = viewModel.getTimePlan()
//        timePlan?.let {
//            with(binding.recyclerviewTimePlan) {
//                adapter = TasksRecyclerAdapter(mapOf(1 to "Code", 2 to "Misc", 3 to "Work"), it, this@TimePlanFragment)
//                layoutManager = LinearLayoutManager(requireContext())
//            }
//        }
    }

    override fun onAddClicked() {
//        val lastTimePlan = viewModel.getTimePlan()?.last()

    }
}