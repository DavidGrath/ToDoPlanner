package com.example.todoplanner

import com.example.todoplanner.domain.entities.framework.database.TaskData

interface TempPlanner {
    fun startDay(day: List<TaskData>)
    fun addTaskObserver(observer: TempObservable.TempObserver<TaskData>)
    fun addCurrentTaskIDListener(observer: TempObservable.TempObserver<Int>)
    fun notify(task: TaskData)
    fun notifyBulk(tasks: List<TaskData>)
    fun endDay()
    fun dayStart(): Int
    fun dayEnd(): Int
}