package com.example.contactsapimvvm.network

import com.example.contactsapimvvm.entities.ContactListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInstance {

    @GET("api/users?page=2")
    fun getDataFromApi(@Query("q") query: String): Call<ContactListData>
}