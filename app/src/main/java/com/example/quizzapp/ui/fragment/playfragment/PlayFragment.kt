package com.example.quizzapp.ui.fragment.playfragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentPlayBinding
import com.example.quizzapp.ui.dialog.CustomDialog

class PlayFragment : Fragment() {
    private lateinit var binding : FragmentPlayBinding
    private val args: PlayFragmentArgs by navArgs()
    private lateinit var playViewModel : PlayViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentPlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the SavedStateHandle from the NavBackStackEntry
        val savedStateHandle = findNavController().currentBackStackEntry?.savedStateHandle
        Log.d("State Save", "${savedStateHandle?.get<Int>("currentIndex")}")
        // Ensure that savedStateHandle is not null
        if (savedStateHandle != null) {
            playViewModel = ViewModelProvider(
                requireActivity(),
                PlayViewModel.PlayModelFactory(requireActivity().application, savedStateHandle)
            )[PlayViewModel::class.java]
        } else {
            // Handle the case where savedStateHandle is null if necessary
        }
        //tool bar
        // Set up the toolbar
        val toolbar = binding.toolbar
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            // Handle the back button click
            findNavController().navigate(R.id.action_playFragment_to_homeFragment)
        }


        // recieve question category
        //val args :PlayFragmentArgs by navArgs()
        val messenger = args.IdQuestionCategory
        //
        playViewModel.getQuestionsFromCategory(messenger)
        playViewModel.listQuestion.observe(viewLifecycleOwner, Observer {
            questions->
            if(questions != null){
                if (questions.isNotEmpty()) {
                    val index = playViewModel.currentIndex.value ?: 0
                    binding.tvQuestion.text = questions[index].questionName
                    binding.tvAns1.text = questions[index].listAnswer!![0]
                    binding.tvAns2.text = questions[index].listAnswer!![1]
                    binding.tvAns3.text = questions[index].listAnswer!![2]
                    binding.tvAns4.text = questions[index].listAnswer!![3]
                }
            }else {
                binding.tvQuestion.text = "No questions available"
            }

        })
        // Set click listeners for answers
        onClickAnswer()
        //check correct show dialog
        playViewModel.isCorrect.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { isCorrect ->
                showResultDialog(isCorrect)
            }
        })
        binding.linhtinh.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_playFragment_to_winFragment)
        }
        playViewModel.isComplete.observe(viewLifecycleOwner, Observer {
            isComplete->if(isComplete == true){
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.playFragment,true)
                    .build()
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(R.id.action_playFragment_to_winFragment, null, navOptions)
            }, 1500)
        }
        })
    }
    //onclick answer
    private fun onClickAnswer() {
        binding.tvAns1.setOnClickListener { checkAnswer(binding.tvAns1.text.toString())  }
        binding.tvAns2.setOnClickListener { checkAnswer(binding.tvAns2.text.toString()) }
        binding.tvAns3.setOnClickListener { checkAnswer(binding.tvAns3.text.toString()) }
        binding.tvAns4.setOnClickListener { checkAnswer(binding.tvAns4.text.toString()) }
    }
    //show dialog
    private fun showResultDialog(isCorrect: Boolean) {
        val dialog = CustomDialog.newInstance(isCorrect)
        dialog.show(childFragmentManager, "CustomDialog")
    }

    //check correct
    private fun checkAnswer(answer: String) {
        val currentQuestion = playViewModel.currentQuestion()
        if(currentQuestion != null){
            playViewModel.checkCorrect(answer, currentQuestion)
        }




    }

    override fun onStop() {
        super.onStop()
        Log.d("this is current index on stop", "${playViewModel.currentIndex.value}")
        playViewModel.saveCurrentIndex(playViewModel.currentIndex.value ?: 0)
    }

    override fun onResume() {
        super.onResume()
        Log.d("this is current index from on resume", "${playViewModel.currentIndex.value}")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Ondetach", " Called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("On destroy view", " Called")
    }


}