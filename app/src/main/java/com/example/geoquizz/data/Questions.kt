package com.example.geoquizz.data
import com.example.geoquizz.R

data class Question(
    val id : Int,   // id of question
    val textRes: Int, //text resource
    val answer: Boolean // expected correct answer
)

// match question with correct answer and id
val questionBank = listOf(
    Question(0, R.string.question_oceans, true),
    Question(1,R.string.question_mideast, false),
    Question(2,R.string.question_africa, false),
    Question(3,R.string.question_americas, true),
    Question(4,R.string.question_asia, true)
)