package com.example.contactsapimvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactsapimvvm.entities.ContactListData
import com.example.contactsapimvvm.entities.Data
import com.example.contactsapimvvm.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var contactListData: MutableLiveData<ContactListData> = MutableLiveData()


    fun getContactData(): LiveData<List<Data>> {
        return repository.getAllContacts()
    }

    fun getContactListDataObserver(): MutableLiveData<ContactListData> {
        return contactListData
    }

    fun loadContactListData() {
        repository.getContactList("q", contactListData)
    }

}