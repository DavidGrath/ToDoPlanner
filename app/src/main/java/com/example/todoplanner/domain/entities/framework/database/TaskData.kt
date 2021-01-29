package com.example.todoplanner.domain.entities.framework.database

//@Entity
data class TaskData(
//        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val start: Long,
        val end: Long,
        val todoId: Long,
        val category: Int,
        val name: String,
        val status: Int
)