package com.example.geoquizz.data

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.example.geoquizz.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.compose.runtime.collectAsState

class ContentViewModel(private val questions: List<Question> = questionBank) : ViewModel() {

    private val question = questionBank //list of questions
    private val correctAnsweredIDs = mutableSetOf<Int>() //which ones are correct
    private var textResolver: ((Int) -> String)? = null
    private val _uiState = MutableStateFlow(
        QuizUIState(
            currQuesText = questions.first().textRes.toString(), // temporarily show text ID as string
            currQuesIndex = 0,
            totalQues = questions.size
        )
    ) //save state
    val uiState: StateFlow<QuizUIState> = _uiState.asStateFlow()

    fun setQuestionTextResolver(resolver: (Int) -> String) {
        textResolver = resolver
        // Immediately update the current question text
        updateCurrentQuestionText()
    }

    private fun updateCurrentQuestionText() {
        val index = _uiState.value.currQuesIndex
        val resolver = textResolver
        val question = questions[index]

        val text = if (resolver != null) {
            resolver(question.textRes)
        } else {
            question.textRes.toString() // fallback placeholder
        }

        _uiState.update { it.copy(currQuesText = text) }
    }

    fun checkAns(usrAns: Boolean) {
        val currState = _uiState.value
        val currIndex = currState.currQuesIndex
        val currQuest = questions[currIndex]

        val isCorr = usrAns == currQuest.answer
        if (isCorr && !correctAnsweredIDs.contains(currQuest.id)) {
            correctAnsweredIDs.add(currQuest.id)
            _uiState.update { it.copy(score = it.score + 1, lastAnswerCorrect = true) }
        } else {
            _uiState.update { it.copy(lastAnswerCorrect = false) }
        }
    }

    fun resetAnswerState() {
        _uiState.update { it.copy(lastAnswerCorrect = null) }
    }

    fun nextQuest() {
        _uiState.update { current ->
            val total = questions.size
            val nextIndex = (current.currQuesIndex + 1) % total
            current.copy(
                currQuesIndex = nextIndex,
                currQuesText = questions[nextIndex].textRes.toString() // placeholder
            )
        }
        updateCurrentQuestionText()
    }

    fun prevQuest() {
        _uiState.update { current ->
            val total = questions.size
            val prevIndex = if (current.currQuesIndex - 1 < 0) total - 1 else current.currQuesIndex - 1
            current.copy(
                currQuesIndex = prevIndex,
                currQuesText = questions[prevIndex].textRes.toString() // placeholder
            )
        }
        updateCurrentQuestionText()
    }
}

