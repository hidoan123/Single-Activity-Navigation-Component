package com.example.quizzapp.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizzapp.data.database.entities.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user : UserEntity)

    @Query("SELECT *FROM users")
    fun getUser() : LiveData<List<UserEntity>>
}