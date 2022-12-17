package com.example.contactsapimvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactsapimvvm.entities.Data

@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class RoomDbApp : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        private var INSTANCE: RoomDbApp? = null
        fun getRoomAppDb(context: Context): RoomDbApp {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<RoomDbApp>(
                    context.applicationContext, RoomDbApp::class.java, "RoomDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}