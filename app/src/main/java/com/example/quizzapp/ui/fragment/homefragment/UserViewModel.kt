package com.example.quizzapp.ui.fragment.homefragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.data.database.entities.UserEntity
import com.example.quizzapp.data.model.User
import com.example.quizzapp.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(app : Application) : ViewModel() {

    private val userRepository : UserRepository = UserRepository(app)
    private val _isInsertSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val isInsertSuccess : LiveData<Boolean> get()= _isInsertSuccess

    fun getAllUser() : LiveData<List<UserEntity>> = userRepository.getListUser()

    fun insertUser(user : UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        try {
            userRepository.insertUser(user)
            _isInsertSuccess.postValue(true)
        }catch (ex : Exception){
            _isInsertSuccess.postValue(false)
        }
    }

    class UserViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(application) as T
            }

            throw IllegalArgumentException("Unable construct viewModel")
        }

    }
}