package com.example.quizzapp.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.quizzapp.data.database.AppDatabase
import com.example.quizzapp.data.database.daos.QuestionDao
import com.example.quizzapp.data.database.entities.QuestionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionRepository(app : Application) {
    private val questionDao : QuestionDao
    init {
        val database = AppDatabase.getDatabase(app)
        questionDao = database.questionDao()
    }

    suspend fun insertQuestion(question : QuestionEntity) = withContext(Dispatchers.IO){
        questionDao.insertQuestion(question)
    }

     fun getAllQuestion() : LiveData<List<QuestionEntity>> = questionDao.getAllQuestion()

    fun getListQuestion(idCategory : Int) : LiveData<List<QuestionEntity>> = questionDao.getQuestionFromId(idCategory)

    suspend fun insertData() = withContext(Dispatchers.IO){
        val sampleData = listOf(
            QuestionEntity(1,
                "Find the value of x in the equation:2x+5=15",
                1,
                listAnswer = listOf("5","10","7","3"),
                "5"
                ),
            QuestionEntity(2,
                "Calculate the value of the expression: 4*4 − 3*3",
                1,
                listAnswer = listOf("7","9","16","25"),
                "7"
            ),
            QuestionEntity(3,
                "The area of a square with a side length of 8 cm is:",
                1,
                listAnswer = listOf("7","9","64","54"),
                "64"
            ),
            QuestionEntity(4,
                "If x + 2 y = 10  and x − y = 4, what is the value of x?",
                1,
                listAnswer = listOf("6","8","10","12"),
                "8"
            ),
            QuestionEntity(5,
                "The sum of three consecutive integers is 21. Find the smallest of these three numbers",
                1,
                listAnswer = listOf("6","7","10","12"),
                "7"
            ),

            QuestionEntity(6,
                "What is the largest country by land area in the world?",
                6,
                listAnswer = listOf("United States","Canada","Russia","China"),
                "Russia"
            ),
            QuestionEntity(7,
                "What is the longest mountain range in the world?",
                6,
                listAnswer = listOf("Alps","Himalayas","Andes","Rockies"),
                "Andes"
            ),
            QuestionEntity(8,
                "What is the capital of Australia?",
                6,
                listAnswer = listOf("Sydney","Melbourne","Canberra","Brisbane"),
                "Canberra"
            ),
            QuestionEntity(9,
                "What is the longest river in the world?",
                6,
                listAnswer = listOf("Amazon River","Nile River","Mississippi River","Yangtze River"),
                "Nile River"
            ),
            QuestionEntity(10,
                "What is the smallest continent by land area?",
                6,
                listAnswer = listOf("Asia","Europe","Africa","Australia"),
                "Australia"
            )

        )
        for(question in sampleData){
            questionDao.insertQuestion(question)
        }
    }
}