package com.example.todoplanner.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoplanner.TempPlanner
import com.example.todoplanner.data.TodoRepository
import com.example.todoplanner.framework.TodoDatabase
import com.example.todoplanner.usecase.HomeUseCase

class TodoViewModel(val app: Application, planner: TempPlanner) : AndroidViewModel(app) {

    val repository: TodoRepository

    lateinit var homeUseCase: HomeUseCase

    init {
        val database = TodoDatabase.getDatabase(/*app.applicationContext*/)
        repository = TodoRepository(database, planner)

    }

//    fun startDay() = homeUseCase.startDay()

}