package com.example.quizzapp.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizzapp.data.database.entities.QuestionEntity

@Dao
interface QuestionDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuestion(question: QuestionEntity)

    @Query("SELECT *FROM questions WHERE questionCategory = :idCategory")
     fun getQuestionFromId(idCategory : Int) : LiveData<List<QuestionEntity>>

    @Query("SELECT *FROM questions")
     fun getAllQuestion() : LiveData<List<QuestionEntity>>

}