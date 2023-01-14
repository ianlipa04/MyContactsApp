package com.example.contactsapimvvm.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contacts")
data class Data(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "avatar") val avatar: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "first_name") val first_name: String,
    @ColumnInfo(name = "last_name") val last_name: String

)