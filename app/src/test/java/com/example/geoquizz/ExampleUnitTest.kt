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

}