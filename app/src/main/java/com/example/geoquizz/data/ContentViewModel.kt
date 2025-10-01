package com.example.geoquizz.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.geoquizz.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContentViewModel(application: Application) : AndroidViewModel(application) {

    private val question = questionBank //list of questions
    private val correctAnsweredIDs = mutableSetOf<Int>() //which ones are correct

    private val _uiState: MutableStateFlow<QuizUIState> //save state
    val uiState: StateFlow<QuizUIState> // keep track of ui

    init {
        val firstQuestTxt = getQuestText(question.first().textRes)
        _uiState = MutableStateFlow(
            QuizUIState(
                firstQuestTxt,
                question.size
            )
        )
        uiState = _uiState.asStateFlow()
    }

    private fun getQuestText(resId: Int): String {
        return getApplication<Application>().getString(resId)
    }

    fun checkAns(usrAns: Boolean) {
        val currState = _uiState.value //get new value
        val currIndex = currState.currQuesIndex
        val currQuest = question[currIndex]

        val isCorr = usrAns == currQuest.answer // check if answer is correct
        if (isCorr && !correctAnsweredIDs.contains(currQuest.id)) { // only count once
            correctAnsweredIDs.add(currQuest.id)
            _uiState.update {
                it.copy(score = it.score + 1)
            } //award a point and save
        }

    }

    fun nextQuest(){
        _uiState.update { currentState->
            val total = question.size
            val nextIndex = (currentState.currQuesIndex + 1) % total
            currentState.copy(
                currQuesIndex = nextIndex,
                currQuesText = getQuestText(question[nextIndex].textRes)
            )
        }
    }

    fun prevQuest(){ // almost same as next quest
        _uiState.update { currentState->
            val total = question.size
            val prevIndex = (currentState.currQuesIndex - 1) % total
            currentState.copy(
                currQuesIndex = prevIndex,
                currQuesText = getQuestText(question[prevIndex].textRes)
            )
        }
    }
}

