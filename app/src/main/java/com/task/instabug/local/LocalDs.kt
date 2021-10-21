package com.task.instabug.local

import com.task.instabug.network.models.WordsResponse

interface LocalDs {
    fun saveWords(rawData: WordsResponse)
    fun getCachedResult(): WordsResponse
}