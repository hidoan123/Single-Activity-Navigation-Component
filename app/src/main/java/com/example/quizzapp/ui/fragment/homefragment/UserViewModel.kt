package com.example.quizzapp.ui.fragment.homefragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.data.database.entities.QuestionCategoryEntity
import com.example.quizzapp.data.database.entities.UserEntity
import com.example.quizzapp.data.model.User
import com.example.quizzapp.data.repositories.QuestionCategoryRepository
import com.example.quizzapp.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(app : Application, private val state : SavedStateHandle) : ViewModel() {
    //user
    private val userRepository : UserRepository = UserRepository(app)
    private val _isInsertSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val isInsertSuccess : LiveData<Boolean> get()= _isInsertSuccess
    //questioncategory
    private val questionCategoryRepository : QuestionCategoryRepository = QuestionCategoryRepository(app)
    //get user
    fun getAllUser() : LiveData<List<UserEntity>> = userRepository.getListUser()
    //insert user
    fun insertUser(user : UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        try {
            userRepository.insertUser(user)
            _isInsertSuccess.postValue(true)
        }catch (ex : Exception){
            _isInsertSuccess.postValue(false)
        }
    }
    //position scroll recyclerview
    var scrollPosition : Int
        get() = state.get("scroll_position") ?: 0
        set(value) = state.set("scroll_position", value)


    //get questioncategory
     fun getQuestionCategory() : LiveData<List<QuestionCategoryEntity>> = questionCategoryRepository.getListQuestionCategory()


    class UserViewModelFactory(private val application: Application,
                               private val state : SavedStateHandle) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(application,state) as T
            }

            throw IllegalArgumentException("Unable construct viewModel")
        }

    }
}