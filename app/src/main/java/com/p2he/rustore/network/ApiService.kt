package com.p2he.rustore.network

import com.p2he.rustore.model.AppModel
import retrofit2.http.GET

interface ApiService {
    @GET("apps")
    suspend fun getApps(): List<AppModel>
}
