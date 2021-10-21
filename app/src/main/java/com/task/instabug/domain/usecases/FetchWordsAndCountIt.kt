package com.task.instabug.domain.usecases

import com.task.instabug.data.models.WordPresentationModel
import com.task.instabug.domain.repositories.WordsRepo

class FetchWordsAndCountIt(private val wordsRepo: WordsRepo) {

    fun apply(): WordPresentationModel {
        return wordsRepo.fetchData()
    }
}