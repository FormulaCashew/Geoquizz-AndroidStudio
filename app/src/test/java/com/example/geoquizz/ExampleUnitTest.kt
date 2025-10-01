package com.example.geoquizz.data

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import android.app.Application

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TestApplication : Application() {
}

class ContentViewModelUnitTest {

    private val testApplication = TestApplication()

    private lateinit var viewModel: ContentViewModel
    private val totalQuestions = questionBank.size // Should be 5

    @Before
    fun setup() {
        viewModel = ContentViewModel(testApplication)
    }

    @Test
    fun viewModel_initialization_setsCorrectState() {
        val initialIndex = viewModel.uiState.value.currQuesIndex

        viewModel.nextQuest()

        val newIndex = viewModel.uiState.value.currQuesIndex

        //check for changed index
        assertEquals(initialIndex + 1, newIndex)
        assertEquals(1, newIndex)
    }
    @Test
    fun checkAns_stateIsPersistedAfterMove() {

        viewModel.checkAns(true)

        val finalState = viewModel.uiState.value

        //Check if value is saved
        assertEquals(1, finalState.currQuesIndex)
        assertEquals(1, finalState.score)
    }

    @Test
    fun checkAns_correctAnswerGivenTwice_scoreIncrementsOnlyOnce() {
        viewModel.checkAns(true)
        viewModel.nextQuest()
        viewModel.prevQuest()
        viewModel.checkAns(true)

        //Check if correct ans is saved
        assertEquals(1, viewModel.uiState.value.score)
    }
}