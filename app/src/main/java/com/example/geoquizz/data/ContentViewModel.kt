package com.example.geoquizz.data

import android.app.Application
import androidx.databinding.tool.util.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContentViewModel(application: Application) : AndroidViewModel(application) {

    private val questions: List<Questions> //list of questions
    private val CorrectAnsID = mutableSetOf<Int>() //which ones are correct

    private val _uiState : MutableStateFlow<QuizStateUI> //save state
    val uiState : StateFlow<QuizStateUI> // keep track of ui

    init {
        questions = loadQuestions()
    }

    private fun loadQuestions(){
        val stringArr = getApplication<Application>().Resources.getStringArray
    }

}