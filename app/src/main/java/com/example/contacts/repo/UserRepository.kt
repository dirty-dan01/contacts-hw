package com.example.contacts.repo

import com.example.contacts.data.User

class UserRepository(private val api: ApiService) {
    suspend fun fetchUsers(): List<User> = api.getUsers()
}