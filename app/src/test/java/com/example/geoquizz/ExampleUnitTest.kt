package com.example.geoquizz.data

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ContentViewModelTest  {

    private lateinit var viewModel: ContentViewModel

    @Before
    fun setup() {
        viewModel = ContentViewModel()
    }

    @Test
    fun nextQuestion_changesIndex() {
        // check if question index changes
        val initialIndex = viewModel.uiState.value.currQuesIndex
        viewModel.nextQuest()
        val newIndex = viewModel.uiState.value.currQuesIndex
        assertEquals((initialIndex + 1) % questionBank.size, newIndex)
    }

    @Test
    fun prevQuestion_wrapsAroundCorrectly() {
        // from 0 wrap to last
        viewModel.prevQuest()
        val newIndex = viewModel.uiState.value.currQuesIndex
        assertEquals(questionBank.size - 1, newIndex)
    }

    @Test
    fun checkAns_correctAnswer_incrementsScoreOnce() {
        // Answer same question correctly again — should not increase score
        val firstQuestion = questionBank[0]
        viewModel.checkAns(firstQuestion.answer)
        val afterFirst = viewModel.uiState.value.score
        assertEquals(1, afterFirst)
        viewModel.checkAns(firstQuestion.answer)
        val afterSecond = viewModel.uiState.value.score
        assertEquals(1, afterSecond)
    }

    @Test
    fun checkAns_incorrectAnswer_doesNotIncrementScore() {
        val firstQuestion = questionBank[0]
        viewModel.checkAns(!firstQuestion.answer)
        val score = viewModel.uiState.value.score
        assertEquals(0, score)
    }

    @Test
    fun nextAndPrev_changeQuestionTextPlaceholder() {
        val initialText = viewModel.uiState.value.currQuesText
        viewModel.nextQuest()
        val nextText = viewModel.uiState.value.currQuesText
        assertNotEquals(initialText, nextText)
    }
}