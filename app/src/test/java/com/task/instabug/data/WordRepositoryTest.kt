package com.task.instabug.data

import com.task.instabug.data.mocks.MockData
import com.task.instabug.data.mocks.MockedLocalDataSource
import com.task.instabug.data.mocks.MockedNetworkProvider
import com.task.instabug.data.mocks.MockedRemoteDataSource
import com.task.instabug.data.repositories.WordsRepository
import com.task.instabug.network.mappers.ignoreSpecialCharacters
import org.junit.Assert.assertEquals
import org.junit.Test


class WordRepositoryTest {


    private  var localDataSource =
        MockedLocalDataSource()

    private  var remoteDataSource=
        MockedRemoteDataSource()

   private  lateinit var networkProvider : MockedNetworkProvider

    private var repository: com.task.instabug.domain.repositories.WordsRepository? = null

    @Test
    fun testIgnoreSpecialCharacters() {
        assertEquals(MockData.htmlResponse.ignoreSpecialCharacters(), MockData.dataResponse.response)
    }

    @Test
    fun testLocalResponse() {
        networkProvider =
            MockedNetworkProvider(false)
        repository = WordsRepository(remoteDataSource, localDataSource, networkProvider)
        val data = repository?.fetchData()

        assertEquals(data, MockData.displayedDataModel)
    }

    @Test
    fun testFetchRemoteData() {
        networkProvider =
            MockedNetworkProvider(true)
        repository = WordsRepository(remoteDataSource, localDataSource, networkProvider)
        val data = repository?.fetchData()
        assertEquals(data, MockData.displayedDataModel)
    }
}

