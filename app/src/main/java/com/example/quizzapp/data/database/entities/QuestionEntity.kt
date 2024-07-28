package com.example.quizzapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val questionName : String,
    val questionCategory : Int,
    val listAnswer : List<String> ?= null,
    val correctAnswer : String
)



