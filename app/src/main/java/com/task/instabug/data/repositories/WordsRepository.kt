package com.task.instabug.data.repositories

import com.task.instabug.data.mappers.toPresentationWordModel
import com.task.instabug.data.models.WordPresentationModel
import com.task.instabug.domain.repositories.WordsRepo
import com.task.instabug.local.LocalDs
import com.task.instabug.network.models.WordsResponse
import com.task.instabug.network.request.RemoteDs
import com.task.instabug.network.state_provider.NetworkProvider
// responsible for fetch the data , count the repeated words
class WordsRepository(
    private val remoteDataSource: RemoteDs,
    private val localDataSource: LocalDs,
    private val networkStateProvider: NetworkProvider
) : WordsRepo {

    override fun fetchData(): WordPresentationModel {
        val textResponse: WordsResponse
        var data: WordPresentationModel? = null



        when (networkStateProvider.isConnected()) { //check connection
            true -> {
                 textResponse = remoteDataSource.fetchHtmlResponse() // hit the api and get the response
                 data = textResponse.toPresentationWordModel()
                 localDataSource.saveWords(textResponse)
            }
            false -> {
                data = localDataSource.getCachedResult().toPresentationWordModel()
            }
        }
        return data



}
}