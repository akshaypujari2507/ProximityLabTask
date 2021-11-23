package com.proximitylabtask.data.repo

import com.proximitylabtask.data.remote.api.SocketUpdate
import com.proximitylabtask.data.remote.api.WebServicesProvider
import kotlinx.coroutines.channels.Channel

class MainRepository(private val webServicesProvider: WebServicesProvider) {
    fun startSocket(): Channel<SocketUpdate> =
        webServicesProvider.startSocket()

    fun closeSocket() {
        webServicesProvider.stopSocket()
    }
}