package com.example.geoquizz

import android.R.color.black
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.geoquizz.ui.theme.GeoQuizzTheme
import com.example.geoquizz.data.ContentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizzTheme {
                    Geoquizz()
            }
        }
    }
}

@Composable
fun Geoquizz(viewModel: ContentViewModel = viewModel()){
    val uiState by viewModel.uiState.collectAsState()

    Surface(modifier = Modifier
        .fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //questions
            Text(
                text = uiState.currQuesText,
                color = Color.White,
                fontSize = 24.sp
            )
            //Score
            Text(
                text = "Score: ${uiState.score} / ${uiState.totalQues} "
            )
            AnswerButtons(viewModel = viewModel)
            IndexButtons(viewModel = viewModel)
        }
    }
}

@Composable
fun AnswerButtons(viewModel: ContentViewModel){
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Button(
            modifier = Modifier,
            onClick = {viewModel.checkAns(false)}//TODO
        ) {
            Text("False")
        }
        Button(
            modifier = Modifier,
            onClick = {viewModel.checkAns(true)}//TODO
        ) {
            Text("True")
        }
    }
}

@Composable
fun IndexButtons(viewModel: ContentViewModel){
    Row {
        Button(
            modifier = Modifier,
            onClick = {}//TODO
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_back_ios_24),
                contentDescription = "Previous Question"
            )
        }
        Button(
            modifier = Modifier,
            onClick = {}//TODO
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_forward_ios_24),
                contentDescription = "Next Question"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GeoQuizzTheme {
        Geoquizz()
    }
}