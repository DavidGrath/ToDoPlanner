package com.example.todoplanner.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoplanner.TempMapper
import com.example.todoplanner.TempObservable
import com.example.todoplanner.TempPlanner
import com.example.todoplanner.data.TodoRepository
import com.example.todoplanner.domain.entities.framework.database.TaskData
import com.example.todoplanner.domain.entities.ui.TaskUI
import com.example.todoplanner.framework.TodoDatabase
import com.example.todoplanner.usecase.TimePlanUseCase

class TimePlanViewModel(val app: Application, planner: TempPlanner) : AndroidViewModel(app) {

    val useCase: TimePlanUseCase

    init {
        useCase = TimePlanUseCase(
                TodoRepository(TodoDatabase.getDatabase(/*app.applicationContext*/), planner)
        )
    }


    fun getDay(): TempObservable<List<TaskData>> {
        return useCase.getDailyPlan()
    }

    fun updateTaskDone(id: Int) {
        useCase.setTaskDone(id)
    }

    fun transformCurrentList(tasksUI: List<TaskUI>): List<TaskData> {
        return tasksUI.map {
            TempMapper.fromTaskUIToTaskData(it)
        }
    }

    fun startDay(tasks: List<TaskData>) {
        useCase.setDay(tasks)
    }
}