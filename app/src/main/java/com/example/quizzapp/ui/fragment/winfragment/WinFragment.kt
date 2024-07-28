package com.example.quizzapp.ui.fragment.winfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentWinBinding


class WinFragment : Fragment() {

    private lateinit var binding : FragmentWinBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentWinBinding.inflate(inflater, container, false)
        return binding.root
    }

}