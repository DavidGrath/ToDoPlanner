package com.example.todoplanner.usecase

import com.example.todoplanner.TempObservable
import com.example.todoplanner.data.TodoRepository
import com.example.todoplanner.domain.entities.framework.database.TaskData

class TimePlanUseCase(val repository: TodoRepository) {
    fun getDailyPlan(): TempObservable<List<TaskData>> {
        return repository.getTodaysTasks()
    }

    fun setDay(taskList: List<TaskData>) {
        repository.setDay(taskList)
    }

    fun setTaskDone(id: Int) {
        repository.setTaskDone(id)
    }
}