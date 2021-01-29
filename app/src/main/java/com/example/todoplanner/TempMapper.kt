package com.example.todoplanner

import com.example.todoplanner.domain.entities.framework.database.TaskData
import com.example.todoplanner.domain.entities.ui.TaskUI

class TempMapper {
    companion object {
        fun fromTaskDataToTaskUI(from: TaskData): TaskUI {
            with(from) {
                return TaskUI(id, start, end, todoId, category, name, status)
            }
        }

        fun fromTaskUIToTaskData(from: TaskUI): TaskData {
            with(from) {
                return TaskData(id, start, end, todoId, category, name, status)
            }
        }
    }
}