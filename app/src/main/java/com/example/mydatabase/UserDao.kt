package com.example.mydatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT*FROM Users_table")
    fun getAllUsers(): List<User>

    @Query("SELECT*FROM Users_table WHERE id =:id")
    fun getUserById(id: Int): User

    @Query("SELECT*FROM Users_table WHERE age < :minAge")
    fun getUserByMinimumAge(minAge: Int): List<User>

    @Insert
    fun addUser(user: User)

    @Insert
    fun addUserList(user: List<User>)

    @Update
    fun updateUser(user: User)

    @Query("UPDATE Users_table Set age=:age,name=:name WHERE id=:id")
    fun updateUserByName( id:Int,name: String, age: Int)

    @Delete
    fun deleteUser(user: User)

    @Query("Delete FROM Users_table WHERE name=:name")
    fun deleteUserManually(name: String)




}
