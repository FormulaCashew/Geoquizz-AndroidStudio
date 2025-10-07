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
}