package com.task.instabug.di

import android.content.Context
import com.task.instabug.local.LocalDs
import com.task.instabug.local.SharedPreferencesProvider
import com.task.instabug.local.WordsDb
import com.task.instabug.network.request.RemoteDs
import com.task.instabug.network.request.Service
import com.task.instabug.network.state_provider.NetworkProvider
import com.task.instabug.network.state_provider.NetworkStateProvider

class DataDi(context: Context) {
    // responsible for check on network , hit api ,

    private val networkProvider: NetworkProvider = NetworkStateProvider(context)

    private val apiService: RemoteDs = Service("https://instabug.com")

    private val localDataSource: LocalDs = WordsDb(SharedPreferencesProvider(), context)

    val repository: com.task.instabug.domain.repositories.WordsRepo =
        com.task.instabug.data.repositories.WordsRepository(apiService, localDataSource, networkProvider)
}