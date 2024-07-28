package com.example.quizzapp.ui.fragment.homefragment

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.quizzapp.R
import com.example.quizzapp.data.database.entities.QuestionCategoryEntity
import com.example.quizzapp.databinding.FragmentHomeBinding
import com.example.quizzapp.ui.adapter.QuestionCategoryAdapter
import com.example.quizzapp.ui.fragment.playfragment.PlayViewModel

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val userViewModel: UserViewModel by lazy {
        //val savedInstanceState = findNavController().currentBackStackEntry?.savedStateHandle!!
        ViewModelProvider(
            this,
            UserViewModel.UserViewModelFactory(requireActivity().application, SavedStateHandle())
        )[UserViewModel::class.java]
    }
    private lateinit var adapter : QuestionCategoryAdapter
    private var listQuestionCategory = ArrayList<QuestionCategoryEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = findNavController()
        checkUser(controller)
        bindData(controller)
    }

    //bindData function
    private fun bindData(controller : NavController) {
        adapter = QuestionCategoryAdapter{
            // sent question category id
            questionCategory->
            val action = HomeFragmentDirections.actionHomeFragmentToPlayFragment(questionCategory.id)
            controller.navigate(action)
        }
        binding.rvcQuestionCategory.adapter = adapter
        binding.rvcQuestionCategory.layoutManager = LinearLayoutManager(requireContext())

        userViewModel.getQuestionCategory().observe(viewLifecycleOwner, Observer{
                questionCategories->
            questionCategories?.let{
                listQuestionCategory.clear()
                listQuestionCategory.addAll(questionCategories)
                adapter.submitList(listQuestionCategory)
                (binding.rvcQuestionCategory.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(userViewModel.scrollPosition, 0)
            }
        })
    }

    //checkUser function
    private fun checkUser(controller : NavController) {
        userViewModel.getAllUser().observe(viewLifecycleOwner, Observer {
                users->if(users.isEmpty()){
            controller.navigate(R.id.inputnameFragment)
        }
        })
    }
    //
    override fun onPause() {
        super.onPause()
        // Lưu trạng thái cuộn
        val layoutManager = binding.rvcQuestionCategory.layoutManager as LinearLayoutManager
        val scrollPosition = layoutManager.findFirstVisibleItemPosition()
        userViewModel.scrollPosition = scrollPosition
        Log.d("Vi tri cuon", " ${userViewModel.scrollPosition}")
    }
}