package com.example.todoplanner.usecase

import com.example.todoplanner.data.TodoRepository
import com.example.todoplanner.domain.entities.ui.OverviewLegend

class OverviewUseCase(val repository: TodoRepository) {
    fun getTasksSummarizedByCategory(map: Map<Int, String>): List<OverviewLegend> {
        val categories = repository.getCategories()
        val totalTime = repository.getTotalTime()
        val legends = ArrayList<OverviewLegend>()
        for (category in categories) {
            val catTime = repository.getTotalTimeCategory(category)
            legends.add(OverviewLegend(
                    map.getOrElse(category) { "" },
                    catTime.toFloat() / totalTime.toFloat() * 100,
                    catTime
            ))
        }
        return legends
    }
}