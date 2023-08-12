package com.example.mydatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users_table")
data class User(
    @PrimaryKey(autoGenerate = true)
val id:Int=0,
    val name:String,
val age:Int
)