package com.example.todoplanner.framework.daos

import com.example.todoplanner.Constants
import com.example.todoplanner.TempObservable
import com.example.todoplanner.domain.entities.framework.database.TaskData
import java.util.*

//@Dao
/*abstract */class TasksDao {


    val tasksListObservable = TempObservable<List<TaskData>>()
    val calendar = Calendar.getInstance()
    val tasksList = mutableListOf(
            TaskData(23, calendar.timeInMillis + 30_000L, calendar.timeInMillis + 60_000L, 1, 1, "Fight Crime", Constants.TASK_STATUS_PENDING),
            TaskData(113, calendar.timeInMillis + 60_100L, calendar.timeInMillis + 90_000L, 1, 1, "Blow Bubbles", Constants.TASK_STATUS_PENDING),
            TaskData(14, calendar.timeInMillis + 90_000L, calendar.timeInMillis + 120_000L, 1, 1, "Feels Baaddd", Constants.TASK_STATUS_PENDING),
    )

    init {

        tasksListObservable.updateValue(tasksList)
    }

    //    @Query("SELECT * FROM TaskData")
    /*abstract */fun getTasks(): TempObservable<List<TaskData>> {
        return tasksListObservable
    }

    fun getTask(id: Int): TaskData {
        val possibleTask = tasksList.find {
            it.id == id
        }
        return possibleTask!!
    }

    fun setTaskStatus(id: Int) {
        val possibleTask = tasksList.find {
            it.id == id
        }
        if (possibleTask != null) {
            val index = tasksList.indexOf(possibleTask)
            with(possibleTask) {
                val doneTask = TaskData(id, start, end, todoId, category, name, Constants.TASK_STATUS_DONE)
                tasksList[index] = doneTask
            }
            tasksListObservable.updateValue(tasksList)
        }
    }
//    @Insert
//    abstract fun insertTask(taskData : TaskData)
//    @Insert
//    abstract fun insertTasks(taskData : List<TaskData>)
//    @Query("SELECT * FROM TaskData WHERE start >= :start AND `end` <= :end")
//    abstract fun getTasksTimeSlice(start : Long, end : Long) : List<TaskData>
}