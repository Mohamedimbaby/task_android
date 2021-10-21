package com.task.instabug.data.mappers

import android.util.Log
import com.task.instabug.data.models.WordPresentationModel
import com.task.instabug.network.mappers.toStringList
import com.task.instabug.network.models.WordsResponse


internal fun WordsResponse.toPresentationWordModel(): WordPresentationModel {
    val data = HashMap<String, Int>()
    this.response.toStringList().forEach {
        var count = data[it] ?: 0
        data[it] = ++count
    }
    return WordPresentationModel(data)
}