package com.example.todoplanner.usecase

import com.example.todoplanner.Constants
import com.example.todoplanner.TempObservable
import com.example.todoplanner.TempPlanner
import com.example.todoplanner.data.TodoRepository
import com.example.todoplanner.domain.entities.framework.database.TaskData
import com.example.todoplanner.framework.TodoDatabase
import junit.framework.TestCase

class OverviewUseCaseTest : TestCase() {

    lateinit var databaseTasks: List<TaskData>
    lateinit var overviewUseCase: OverviewUseCase
    override fun setUp() {
        super.setUp()
        databaseTasks = mutableListOf(
                TaskData(23, 30_000L, 60_000L, 1, Constants.CATEGORY_MISC, "Fight Crime", Constants.TASK_STATUS_PENDING),
                TaskData(113, 60_000L, 90_000L, 1, Constants.CATEGORY_CODE, "Blow Bubbles", Constants.TASK_STATUS_PENDING),
                TaskData(14, 90_000L, 120_000L, 1, Constants.CATEGORY_CODE, "Feels Baaddd", Constants.TASK_STATUS_PENDING),
        )
        overviewUseCase = OverviewUseCase(TodoRepository(TodoDatabase.getDatabase(), object : TempPlanner {
            override fun startDay(day: List<TaskData>) {

            }

            override fun addTaskObserver(observer: TempObservable.TempObserver<TaskData>) {

            }

            override fun addCurrentTaskIDListener(observer: TempObservable.TempObserver<Int>) {

            }

            override fun notify(task: TaskData) {

            }

            override fun notifyBulk(tasks: List<TaskData>) {

            }

            override fun endDay() {

            }

            override fun dayStart(): Int {
                return 0
            }

            override fun dayEnd(): Int {
                return 0
            }
        }))
    }

    fun testGetTasksSummarizedByCategory() {
        val legends = overviewUseCase.getTasksSummarizedByCategory(mapOf(Constants.CATEGORY_CODE to "Code", Constants.CATEGORY_MISC to "Misc"))
        assertEquals(66.67F, legends[legends.indexOf(legends.find { it.title == "Code" }!!)].percentage, 0.02F)
    }
}