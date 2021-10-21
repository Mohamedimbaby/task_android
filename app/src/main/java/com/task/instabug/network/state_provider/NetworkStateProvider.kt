package com.task.instabug.network.state_provider

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkStateProvider(private val context: Context) :
    NetworkProvider {

    override fun isConnected(): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val currentState =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                currentState.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                currentState.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                currentState.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }
}