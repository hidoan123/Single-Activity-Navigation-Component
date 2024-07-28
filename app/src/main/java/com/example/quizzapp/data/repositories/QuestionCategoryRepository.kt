package com.example.quizzapp.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.quizzapp.R
import com.example.quizzapp.data.database.AppDatabase
import com.example.quizzapp.data.database.daos.QuestionCategoryDao
import com.example.quizzapp.data.database.entities.QuestionCategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionCategoryRepository(app:Application) {
    private  val questionCategoryDao: QuestionCategoryDao
    init {
        val questionDatabase = AppDatabase.getDatabase(app)
        questionCategoryDao = questionDatabase.questionCategoryDao()
    }
    suspend fun insertQuestionCategory(qcE : QuestionCategoryEntity) = withContext(Dispatchers.IO){
        questionCategoryDao.insertQuestionCategory(qcE)
    }
    fun getListQuestionCategory() : LiveData<List<QuestionCategoryEntity>> = questionCategoryDao.getQuestionCategory()


    suspend fun insertSampleData() = withContext(Dispatchers.IO) {
        val sampleData = listOf(
            QuestionCategoryEntity(1, "MATH", R.drawable.math),
            QuestionCategoryEntity(2, "PHYSICS", R.drawable.physics),
            QuestionCategoryEntity(3, "CHEMISTRY", R.drawable.chemistry),
            QuestionCategoryEntity(4, "BIOLOGY", R.drawable.biology),
            QuestionCategoryEntity(5, "HISTORY", R.drawable.history),
            QuestionCategoryEntity(6, "GEOGRAPHY", R.drawable.geography),
            QuestionCategoryEntity(7, "IT", R.drawable.tin),
            QuestionCategoryEntity(8, "LITERATURE",R.drawable.van),
        )
        for (category in sampleData) {
            questionCategoryDao.insertQuestionCategory(category)
        }
    }
}