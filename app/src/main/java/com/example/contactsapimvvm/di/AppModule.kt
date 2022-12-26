package com.example.contactsapimvvm.di

import android.content.Context
import com.example.contactsapimvvm.db.RoomDbApp
import com.example.contactsapimvvm.db.UserDao
import com.example.contactsapimvvm.network.ApiServiceInstance
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

    private const val BASE_URL = "https://reqres.in/"

    @Singleton
    @Provides
    fun getRetrofitInstance(retrofit: Retrofit): ApiServiceInstance {
        return retrofit.create(ApiServiceInstance::class.java)
    }

    @Singleton
    @Provides
    fun retrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getDbApp(context: Context): RoomDbApp {
        return RoomDbApp.getRoomAppDb(context)
    }

    @Singleton
    @Provides
    fun getDao(appDb: RoomDbApp): UserDao {
        return appDb.getUserDao()
    }

    @Provides
    fun provideContext(
        @ApplicationContext context: Context,
    ): Context {
        return context
    }
}