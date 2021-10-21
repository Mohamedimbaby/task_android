package com.task.instabug.data.mocks

import com.task.instabug.local.LocalDs
import com.task.instabug.network.models.WordsResponse

class MockedLocalDataSource(
) : LocalDs {

    override fun saveWords(rawData: WordsResponse) {

    }

    override fun getCachedResult(): WordsResponse {
        return WordsResponse(response = MockData.dataResponse.response)
    }
}