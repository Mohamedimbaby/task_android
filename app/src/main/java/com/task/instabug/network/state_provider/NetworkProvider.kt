package com.task.instabug.network.state_provider

interface NetworkProvider {
    fun isConnected(): Boolean
}