package com.example.demoroy.api

import com.example.demoroy.model.User
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun getUsers(): List<User>
}