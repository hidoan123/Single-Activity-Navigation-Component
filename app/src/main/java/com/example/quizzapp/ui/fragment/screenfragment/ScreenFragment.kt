package com.example.quizzapp.ui.fragment.screenfragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R


class ScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = findNavController()
        SetOnTimeSplash(controller)
    }


    //set on time splash
    private fun SetOnTimeSplash(controller : NavController){
        Handler(Looper.getMainLooper()).postDelayed({
            controller.navigate(R.id.action_screenFragment_to_homeFragment)
        }, 4000)
    }
}

