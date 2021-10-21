package com.task.instabug.di

import android.app.Application

class AppDi(context: Application) {
// application class
    val dataDi = DataDi(context)

    var presentationDi: PresentationDI? = null
}