package com.example.quizzapp.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizzapp.data.database.entities.QuestionCategoryEntity

@Dao
interface QuestionCategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuestionCategory(qcE : QuestionCategoryEntity)

    @Query("SELECT * FROM question_categorys")
    fun getQuestionCategory() : LiveData<List<QuestionCategoryEntity>>

}