package com.satoshi.lightningnodes.data.api

import com.satoshi.lightningnodes.data.response.NodeResponse
import retrofit2.http.GET

interface Api {

    @GET("api/v1/lightning/nodes/rankings/connectivity")
    suspend fun getBestConnectivityNodes(): List<NodeResponse>
}