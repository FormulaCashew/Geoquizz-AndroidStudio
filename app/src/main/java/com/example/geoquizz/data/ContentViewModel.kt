package com.example.geoquizz.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContentViewModel : ViewModel() {
    private val _index = MutableStateFlow(0)
    val index : StateFlow<Int> = _index.asStateFlow()

    private val _ans_count = MutableStateFlow(0)
    val ans_count : StateFlow<Int> = _ans_count.asStateFlow()

    fun increment_index(){
        _index.update { index -> index +1 }
    }

    fun increment_ans_count(){
        _index.update { _ans_count -> _ans_count +1 }
    }
}