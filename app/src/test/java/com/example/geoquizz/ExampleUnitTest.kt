package com.example.geoquizz.data

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TestApplication : Application() {
}

@RunWith(AndroidJUnit4::class)
class ContentViewModelInstrumentedTest {

    private lateinit var viewModel: ContentViewModel

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        viewModel = ContentViewModel(context)
    }

    @Test
    fun nextQuestion_changesIndex() {
        val initial = viewModel.uiState.value.currQuesIndex
        viewModel.nextQuest()
        val newIndex = viewModel.uiState.value.currQuesIndex
        assertEquals((initial + 1) % questionBank.size, newIndex)
    }

//    @Test
//    fun viewModel_initialization_setsCorrectState() {
//        val initialIndex = viewModel.uiState.value.currQuesIndex
//
//        viewModel.nextQuest()
//
//        val newIndex = viewModel.uiState.value.currQuesIndex
//
//        //check for changed index
//        assertEquals(initialIndex + 1, newIndex)
//        assertEquals(1, newIndex)
//    }
//    @Test
//    fun checkAns_stateIsPersistedAfterMove() {
//
//        viewModel.checkAns(true)
//
//        val finalState = viewModel.uiState.value
//
//        //Check if value is saved
//        assertEquals(1, finalState.currQuesIndex)
//        assertEquals(1, finalState.score)
//    }
//
//    @Test
//    fun checkAns_correctAnswerGivenTwice_scoreIncrementsOnlyOnce() {
//        viewModel.checkAns(true)
//        viewModel.nextQuest()
//        viewModel.prevQuest()
//        viewModel.checkAns(true)
//
//        //Check if correct ans is saved
//        assertEquals(1, viewModel.uiState.value.score)
//    }
//    @Test
//    fun checkAns_correctThenIncorrectAnswer_scoreIncrementsOnlyOnce() {
//        viewModel.checkAns(true)
//        viewModel.checkAns(true)
//
//        assertEquals(1, viewModel.uiState.value.score)
//    }
}