package com.example.todoplanner.domain.entities.framework.database

//@Entity
data class GoalData(
//        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val date: Long,
        val type: Int,
        val content: String,
        val done: Boolean,
        var doneDate: Long? = null
)