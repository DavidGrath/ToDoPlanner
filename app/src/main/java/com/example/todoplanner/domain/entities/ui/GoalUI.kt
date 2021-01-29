package com.example.todoplanner.domain.entities.ui

class GoalUI(
        val id: Int,
        val date: Long,
        val type: Int,
        val content: String,
        val done: Boolean,
        var doneDate: Long? = null
)