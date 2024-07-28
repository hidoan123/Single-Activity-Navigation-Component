package com.example.quizzapp.ui.fragment.inputnamefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.data.database.entities.UserEntity
import com.example.quizzapp.data.model.User
import com.example.quizzapp.databinding.FragmentInputnameBinding
import com.example.quizzapp.ui.fragment.homefragment.UserViewModel


class InputnameFragment : Fragment() {

    companion object{
        const val INPUT_NAME_SUCCESS : String = "SUCCESS"
    }
    private lateinit var savedStateHandle: SavedStateHandle
        private lateinit var binding : FragmentInputnameBinding
    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(
            this,
            UserViewModel.UserViewModelFactory(requireActivity().application, SavedStateHandle())
        )[UserViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInputnameBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(INPUT_NAME_SUCCESS, false)
        binding.btnInputName.setOnClickListener {
            val inputString = binding.edtInput.text.toString()
            onclick(inputString)
        }
    }

    private fun onclick(inputString: String) {
        userViewModel.insertUser(UserEntity(1,name = inputString))
        userViewModel.isInsertSuccess.observe(viewLifecycleOwner, Observer {
            isSuccess->
            if(isSuccess){
                savedStateHandle.set(INPUT_NAME_SUCCESS, true)
                findNavController().popBackStack()
            }else{
                Log.d("Input name", "ERROR")
            }
        })
    }


}