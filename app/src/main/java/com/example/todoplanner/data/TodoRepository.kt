package com.example.todoplanner.data

import com.example.todoplanner.TempObservable
import com.example.todoplanner.TempPlanner
import com.example.todoplanner.domain.entities.framework.database.TaskData
import com.example.todoplanner.framework.TodoDatabase
import com.example.todoplanner.framework.daos.GoalsDao
import com.example.todoplanner.framework.daos.TasksDao
import java.util.*

class TodoRepository(private val database: TodoDatabase, val planner: TempPlanner) {

    private val tasksDao: TasksDao = database.tasksDao()
    private val goalsDao: GoalsDao = database.goalsDao()

    var currentTask = TempObservable<TaskData>()

    init {
        planner.addTaskObserver(object : TempObservable.TempObserver<TaskData> {
            override fun onValueChanged(newValue: TaskData) {
                tasksDao.setTaskStatus(newValue.id)
            }
        })
        planner.addCurrentTaskIDListener(object : TempObservable.TempObserver<Int> {
            override fun onValueChanged(newValue: Int) {
                currentTask.updateValue(tasksDao.getTask(newValue))
            }
        })
    }

    fun getTodaysTasks(): TempObservable<List<TaskData>> {
        with(Calendar.getInstance()) {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            val start = timeInMillis
            add(Calendar.DAY_OF_YEAR, 1)
            val end = timeInMillis
            return tasksDao.getTasks()
//            return tasksDao.getTasksTimeSlice(start, end)
        }
    }

    fun setDay(tasks: List<TaskData>) {

    }

    //    fun updateTask(id : Int, status : Boolean, doneDate : Long) {
//
//    }
    fun setTaskDone(id: Int) {
        tasksDao.setTaskStatus(id)
    }

    //    fun setTodaysTasks(tasks : List<TaskData>) {
//        tasksDao.insertTasks(tasks)
//    }
    fun getCategories(): List<Int> {
        return tasksDao.getCategoriesDistinct()
    }

    fun getTotalTimeCategory(category: Int): Long {
        return tasksDao.getCategoryTotalTime(category)
    }

    fun getTasksByCategory(category: Int): List<TaskData> {
        return tasksDao.getTasksByCategory(category)
    }

    fun getTotalTime(): Long {
        return tasksDao.getTotalTime()
    }
}