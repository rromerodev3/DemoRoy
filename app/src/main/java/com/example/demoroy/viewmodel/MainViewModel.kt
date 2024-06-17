package com.example.demoroy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoroy.model.User
import com.example.demoroy.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    private val _users = MutableLiveData<List<User>>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    val users: LiveData<List<User>> get() = _users

    fun getUsers() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val users = userRepository.getRemoteUsers()
            delay(TimeUnit.SECONDS.toMillis(6))
            _users.postValue(users)
            _isLoading.postValue(false)
        }
    }
}