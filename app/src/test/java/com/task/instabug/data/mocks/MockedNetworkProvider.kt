package com.task.instabug.data.mocks

import com.task.instabug.network.state_provider.NetworkProvider

class MockedNetworkProvider(private val shouldPass: Boolean) :
    NetworkProvider {

    override fun isConnected(): Boolean {
        return shouldPass
    }
}