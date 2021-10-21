package com.task.instabug.presentation.models

import com.task.instabug.data.models.WordPresentationModel

sealed class WordsListEventData {
    object LoadingState : WordsListEventData()

    data class SuccessState(val data: WordPresentationModel) : WordsListEventData()

    data class ErrorState(val throwable: Throwable) : WordsListEventData()
}