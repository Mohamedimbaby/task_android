package com.task.instabug.app

import android.app.Application
import com.task.instabug.di.AppDi

class InstaBugApp : Application() {

    lateinit var appDi: AppDi

    override fun onCreate() {
        super.onCreate()

        appDi = AppDi(this)
    }
}