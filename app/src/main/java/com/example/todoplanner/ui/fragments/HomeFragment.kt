package com.example.todoplanner.ui.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todoplanner.databinding.FragmentHomeBinding
import com.example.todoplanner.framework.DayPlannerService
import com.example.todoplanner.ui.viewmodels.TodoViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: TodoViewModel
    val servConn: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as DayPlannerService.DayPlannerBinder

//            viewModel.startDay()
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        requireActivity().bindService(Intent(requireActivity(), DayPlannerService::class.java), servConn, Context.BIND_AUTO_CREATE)
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}