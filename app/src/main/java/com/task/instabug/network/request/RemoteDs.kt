package com.task.instabug.network.request
import com.task.instabug.network.models.WordsResponse

interface RemoteDs {
    fun fetchHtmlResponse(): WordsResponse
}