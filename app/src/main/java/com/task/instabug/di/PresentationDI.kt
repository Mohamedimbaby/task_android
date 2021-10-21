package com.task.instabug.di

import com.task.instabug.domain.usecases.FetchWordsAndCountIt
import com.task.instabug.presentation.viewmodels.WordsFragmentViewModelFactory

class PresentationDI(dataContainer: DataDi) {

    private val useCase = FetchWordsAndCountIt(dataContainer.repository)

    val viewModelFactory = WordsFragmentViewModelFactory(useCase)
}