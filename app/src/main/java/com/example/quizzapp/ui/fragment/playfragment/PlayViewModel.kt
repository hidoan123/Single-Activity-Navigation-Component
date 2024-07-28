package com.example.quizzapp.ui.fragment.playfragment

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.R
import com.example.quizzapp.data.database.entities.QuestionEntity
import com.example.quizzapp.data.repositories.QuestionRepository
import com.example.quizzapp.utils.Event
import kotlinx.coroutines.launch


class PlayViewModel(app: Application,private val state: SavedStateHandle) : ViewModel() {
    //repository
    private val questionRepository: QuestionRepository = QuestionRepository(app)

    //current index
    private val _currentIndex = state.getLiveData<Int>("currentIndex", 0)
    val currentIndex: LiveData<Int> get() = _currentIndex

    //list question
    private val _listQuestion: MutableLiveData<List<QuestionEntity>> = MutableLiveData()
    val listQuestion: LiveData<List<QuestionEntity>> get() = _listQuestion

    //check correct
    private val _isCorrect : MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isCorrect: LiveData<Event<Boolean>> = _isCorrect

    //check complete
    private val _isComplete : MutableLiveData<Boolean> = MutableLiveData()
    val isComplete : LiveData<Boolean> = _isComplete

    //compare
    private val listAnswer : ArrayList<Boolean> = arrayListOf()

    //current question
    fun currentQuestion(): QuestionEntity? {
        val questions = listQuestion.value
        return if (questions != null && _currentIndex.value != null && _currentIndex.value!! < questions.size) {
            questions[_currentIndex.value!!]
        } else {
            null
        }
    }

    fun saveCurrentIndex(index: Int) {
        _currentIndex.value = index
        state.set("currentIndex", index)
    }

    //move to question
    fun moveToQuestion() {
        val questions = listQuestion.value
        val index = _currentIndex.value ?: return
        if (questions != null && index < questions.size - 1) {
            Handler(Looper.getMainLooper()).postDelayed({
                _currentIndex.value = index + 1
                _listQuestion.value = questions!!
            }, 2000)
        }else{
            _isComplete.value = true
            Handler(Looper.getMainLooper()).postDelayed({
               reset()
            }, 1000)
        }
    }
    //


    //get list question from category

    fun getQuestionsFromCategory(idCategory: Int) {
        viewModelScope.launch {
            questionRepository.getListQuestion(idCategory).observeForever { questions ->
                _listQuestion.postValue(questions)
            }
        }
    }

    //check correct
    fun checkCorrect(answer: String, question: QuestionEntity) {
        var isAnswerCorrect = question.correctAnswer.equals(answer, ignoreCase = true)
        _isCorrect.value = Event(isAnswerCorrect)
        moveToQuestion()
        Log.d("Check correct", "$isAnswerCorrect")
        Log.d("Current index", "${currentIndex.value}")
    }

    //complete question
    fun reset(){
        _isComplete.value = false
        _currentIndex.value = 0
        _listQuestion.value = emptyList()
        state.set("currentIndex",0)
    }


    class PlayModelFactory(
        private val application: Application,
        private val state: SavedStateHandle) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PlayViewModel(application,state) as T
            }

            throw IllegalArgumentException("Unable construct viewModel")
        }

    }
}