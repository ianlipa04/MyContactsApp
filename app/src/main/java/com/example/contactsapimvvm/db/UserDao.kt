package com.example.contactsapimvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.contactsapimvvm.entities.Data

@Dao
interface UserDao {

    @Query(value = "SELECT * FROM contacts ORDER BY id DESC")
    fun getAllUserInfo(): LiveData<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContacts(data: Data)

    @Query(value = "DELETE FROM contacts")
    fun deleteAllContacts()

}