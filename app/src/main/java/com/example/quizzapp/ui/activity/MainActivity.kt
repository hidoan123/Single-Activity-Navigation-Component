package com.example.quizzapp.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.quizzapp.R
import com.example.quizzapp.data.database.entities.QuestionCategoryEntity
import com.example.quizzapp.data.repositories.QuestionCategoryRepository
import com.example.quizzapp.data.repositories.QuestionRepository
import com.example.quizzapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
//    private lateinit var repository: QuestionRepository
//    private lateinit var rp : QuestionCategoryRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

//        repository = QuestionRepository(application)
//        rp = QuestionCategoryRepository(application)
//        lifecycleScope.launch { repository.insertData()
//        rp.insertSampleData()
//        }
    }

}