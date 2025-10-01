package com.example.geoquizz.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContentViewModel(application: Application) : AndroidViewModel(application) {

    private val questions = questionBank //list of questions
    private val CorrectAnsID = mutableSetOf<Int>() //which ones are correct

    private val _uiState : MutableStateFlow<QuizStateUI> //save state
    val uiState : StateFlow<QuizStateUI> // keep track of ui

    init {
        val firstQuestTxt = getQuestText(questions.first().textRes)
        _uiState = MutableStateFlow(
            QuizStateUI(
                firstQuestTxt,
                questions.size
            )
        )
        uiState = _uiState.asStateFlow()
    }

    private fun getQuestText(resId:Int): String{
        return getApplication<Application>().getString(resId)
    }

}