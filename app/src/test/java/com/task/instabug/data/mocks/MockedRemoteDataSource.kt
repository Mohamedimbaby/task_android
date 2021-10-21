package com.task.instabug.data.mocks

import com.task.instabug.network.models.WordsResponse
import com.task.instabug.network.request.RemoteDs

class MockedRemoteDataSource() : RemoteDs {

    override fun fetchHtmlResponse(): WordsResponse {
        val dataResponse = WordsResponse()
        dataResponse.response = MockData.dataResponse.response
        return dataResponse
    }
}