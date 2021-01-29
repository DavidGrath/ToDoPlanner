package com.example.todoplanner.domain.entities.ui

data class TaskUI(
        val id: Int,
        val start: Long,
        val end: Long,
        val todoId: Long,
        val category: Int,
        val name: String,
        val status: Int
)