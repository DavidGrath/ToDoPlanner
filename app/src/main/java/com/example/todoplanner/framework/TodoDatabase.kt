package com.example.todoplanner.framework

import android.content.Context
import com.example.todoplanner.framework.daos.GoalsDao
import com.example.todoplanner.framework.daos.TasksDao

//@Database(entities = arrayOf(TaskData::class, GoalData::class), version = TWENTY_JAN_2021)
/*abstract */class TodoDatabase /*: RoomDatabase()*/ {

    /*abstract */fun tasksDao(): TasksDao = TasksDao()
    /*abstract */fun goalsDao(): GoalsDao = GoalsDao()


    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null
        fun getDatabase(context: Context): TodoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
//            synchronized(this) {
            val instance = TodoDatabase()
            /*val instance = Room.databaseBuilder(context.applicationContext,
                TodoDatabase::class.java, "ToDo")
                .fallbackToDestructiveMigration()
                .build()*/
            INSTANCE = instance
            return instance
//            }
        }
    }
}