package com.example.quizzapp.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.quizzapp.data.database.entities.QuestionCategoryEntity

@Dao
interface QuestionCategoryDao {

    @Query("SELECT * FROM question_categorys")
    fun getQuestionCategory() : LiveData<List<QuestionCategoryEntity>>

}