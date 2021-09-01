package com.example.workmanager.networking

import com.example.workmanager.modals.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}