package com.example.geoquizz.data
import com.example.geoquizz.R

data class Questions(
    val id : Int,   // id of question
    val textRes: Int, //text resource
    val answer: Boolean // expected correct answer
)

// match question with correct answer and id
val questionBank = listOf(
    Questions(0, R.string.question_oceans, true),
    Questions(1,R.string.question_mideast, false),
    Questions(2,R.string.question_africa, false),
    Questions(3,R.string.question_americas, true),
    Questions(4,R.string.question_asia, true)
)