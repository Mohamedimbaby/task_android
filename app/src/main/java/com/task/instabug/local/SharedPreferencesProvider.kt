package com.task.instabug.local

import android.content.Context
import android.content.SharedPreferences
import com.task.instabug.local.SharedPreferenceLookUp.WORDS_FILE_KEY

class SharedPreferencesProvider {
    fun provideSettingsPreferences(applicationContext: Context): SharedPreferences =
        applicationContext.getSharedPreferences(WORDS_FILE_KEY, Context.MODE_PRIVATE)
}