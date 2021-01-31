package com.example.todoplanner.ui.viewmodels

import com.example.todoplanner.domain.entities.ui.OverviewLegend
import com.example.todoplanner.usecase.OverviewUseCase

class OverviewViewModel {

    //TODO Remove Map parameter
    lateinit var useCase: OverviewUseCase

    fun getTasksPercentageTotal(tempMap: Map<Int, String>): List<OverviewLegend> {
        return useCase.getTasksSummarizedByCategory(tempMap)
    }

}