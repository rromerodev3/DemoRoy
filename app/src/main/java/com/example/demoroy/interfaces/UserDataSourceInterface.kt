package com.example.demoroy.interfaces

import com.example.demoroy.model.User

interface UserDataSourceInterface {
    suspend fun getRemoteUsers(): List<User>
}