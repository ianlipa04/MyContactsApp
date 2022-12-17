package com.example.contactsapimvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.contactsapimvvm.db.UserDao
import com.example.contactsapimvvm.entities.ContactListData
import com.example.contactsapimvvm.entities.Data
import com.example.contactsapimvvm.network.ApiServiceInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(
    private val apiService: ApiServiceInstance,
    private val dao: UserDao
) {

    fun getAllContacts(): LiveData<List<Data>> {
        return dao.getAllUserInfo()
    }

    fun insetContacts(data: Data) {
        dao.insertContacts(data)
    }

    fun getContactList(query: String, contactListData: MutableLiveData<ContactListData>) {
        val call: Call<ContactListData> = apiService.getDataFromApi(query)
        call.enqueue(object : Callback<ContactListData> {
            override fun onResponse(
                call: Call<ContactListData>,
                response: Response<ContactListData>
            ) {
                if (response.isSuccessful) {
                    dao.deleteAllContacts()
                    response.body()?.data?.forEach {
                        insetContacts(it)
                    }
                    //  contactListData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ContactListData>, t: Throwable) {
                //contactListData.postValue(null)
            }
        })
    }
}