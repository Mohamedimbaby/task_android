package com.task.instabug.di

interface Factory<T> {

    fun create(): T
}