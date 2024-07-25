package com.example.quizzapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_categorys")
data class QuestionCategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val NameQuestionCategory : String,
    val ImageQuestionCategory : Int
    )
