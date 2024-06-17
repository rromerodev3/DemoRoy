package com.example.demoroy.repository

import com.example.demoroy.api.UserService
import com.example.demoroy.interfaces.UserDataSourceInterface
import com.example.demoroy.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: UserService
): UserDataSourceInterface {
    override suspend fun getRemoteUsers(): List<User> {
        return remoteDataSource.getUsers()
    }
}