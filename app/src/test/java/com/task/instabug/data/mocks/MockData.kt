package com.task.instabug.data.mocks

import com.task.instabug.data.models.WordPresentationModel
import com.task.instabug.network.models.WordsResponse

object MockData {
    val htmlResponse = "<html><body><h1>Hello World</h1></body></html>"
    val dataResponse = WordsResponse(response = " html  body  h  Hello World  h    body   html ")
    val responseMap = hashMapOf("html" to 2, "body" to 2, "h" to 2, "Hello" to 1, "World" to 1)
    val displayedDataModel = WordPresentationModel(responseMap)
}