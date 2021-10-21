package com.task.instabug.local

import android.content.Context
import com.task.instabug.local.SharedPreferenceLookUp.WORDS_RESPONSE
import com.task.instabug.network.models.WordsResponse

class WordsDb(
    private val sharedPreferencesProvider: SharedPreferencesProvider,
    private val context: Context
) : LocalDs {

    override fun saveWords(rawData: WordsResponse) {

        sharedPreferencesProvider.provideSettingsPreferences(context).edit().apply {
            putString(WORDS_RESPONSE, rawData.response).commit()
        }.apply()


    }

    override fun getCachedResult(): WordsResponse {
        var cachedResult = ""
        sharedPreferencesProvider.provideSettingsPreferences(context).getString(WORDS_RESPONSE, " ")
            ?.let {
                cachedResult = it
            }
        return WordsResponse(response = cachedResult)
    }
}