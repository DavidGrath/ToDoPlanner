package com.example.todoplanner.ui.viewmodels.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoplanner.TempPlanner
import com.example.todoplanner.ui.viewmodels.TimePlanViewModel

class TimePlanViewModelFactory(val app: Application, val planner: TempPlanner) : ViewModelProvider.AndroidViewModelFactory(app) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TimePlanViewModel(app, planner) as T
    }
}