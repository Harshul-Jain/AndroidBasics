package com.example.mvvm.data.repos

import com.example.mvvm.data.api.Client

object GithubRepository {
    suspend fun getUsers() = Client.api.getUsers()
    suspend fun searchUsers(name: String) = Client.api.searchUser(name)
}