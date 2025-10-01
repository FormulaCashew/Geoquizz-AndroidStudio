package com.example.geoquizz.data

data class QuizUIState (
    val currQuesText: String = "",
    val currQuesIndex: Int = 0,
    val score: Int = 0,
    val totalQues: Int = 0
)