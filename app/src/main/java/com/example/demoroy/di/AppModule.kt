package com.example.demoroy.di

import android.content.Context
import com.example.demoroy.api.UserService
import com.example.demoroy.constants.Constants
import com.example.demoroy.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext appContext: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideUserService(retrofit: Retrofit) = retrofit.create(UserService::class.java)

    @Provides
    fun provideUserRepository(remoteDataSource: UserService) = UserRepository(remoteDataSource)
}