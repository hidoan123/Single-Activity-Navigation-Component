package com.example.quizzapp.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.quizzapp.data.database.AppDatabase
import com.example.quizzapp.data.database.daos.UserDao
import com.example.quizzapp.data.database.entities.UserEntity
import com.example.quizzapp.data.model.User
import com.example.quizzapp.data.model.toUserEntity

class UserRepository(app : Application) {
    private val userDao: UserDao
    init {
        val userDatabase : AppDatabase = AppDatabase.getDatabase(app)
        userDao = userDatabase.userDao()
    }
    suspend fun insertUser(user : UserEntity) = userDao.insertUser(user)
     fun getListUser() : LiveData<List<UserEntity>> = userDao.getUser()
}