package com.example.mydatabase

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=[User::class],version=1)
abstract class DBHandler:RoomDatabase(){
    abstract fun userDao():UserDao
}