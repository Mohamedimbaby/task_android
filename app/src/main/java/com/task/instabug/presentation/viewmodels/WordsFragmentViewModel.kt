package com.task.instabug.presentation.viewmodels

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.instabug.di.Factory
import com.task.instabug.domain.usecases.FetchWordsAndCountIt
import com.task.instabug.presentation.models.WordsListEventData
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.Executors

class WordsFragmentViewModel(private val getWordsUseCase: FetchWordsAndCountIt) : ViewModel() {
    val receivedData = MutableLiveData<WordsListEventData>()

    fun fetchMappedResponse() {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        receivedData.value = WordsListEventData.LoadingState

        executor.execute {
            try {
                val response = getWordsUseCase.apply() // calling apply to perform the whole process

                handler.post {
                    receivedData.value = WordsListEventData.SuccessState(response)
                }
            } catch (e: Exception) {
                handler.post {
                    receivedData.value = WordsListEventData.ErrorState(e)
                }
            }
        }
    }
}

class WordsFragmentViewModelFactory(private val getWordsUseCase: FetchWordsAndCountIt):
    Factory<WordsFragmentViewModel> {

    override fun create(): WordsFragmentViewModel {
        return WordsFragmentViewModel(getWordsUseCase)
    }
}