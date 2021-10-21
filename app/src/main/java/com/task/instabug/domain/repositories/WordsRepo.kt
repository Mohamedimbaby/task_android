package com.task.instabug.domain.repositories

import com.task.instabug.data.models.WordPresentationModel

interface WordsRepo {

    fun fetchData(): WordPresentationModel
}