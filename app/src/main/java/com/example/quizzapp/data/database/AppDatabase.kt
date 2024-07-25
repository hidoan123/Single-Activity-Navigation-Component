package com.example.quizzapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizzapp.data.database.daos.QuestionCategoryDao
import com.example.quizzapp.data.database.daos.UserDao
import com.example.quizzapp.data.database.entities.QuestionCategoryEntity
import com.example.quizzapp.data.database.entities.UserEntity

@Database(entities = [UserEntity::class, QuestionCategoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun questionCategoryDao() : QuestionCategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}