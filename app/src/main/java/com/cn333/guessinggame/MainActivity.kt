package com.cn333.guessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var challengeNumb: Int = 0
    var guessNumb: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessNumberButton = findViewById<Button>(R.id.guessNumberButton)
        val newGameButton = findViewById<Button>(R.id.newGameButton)

        guessNumberButton.setOnClickListener {
            handleGuess()
        }

        newGameButton.setOnClickListener {
            newGame()
        }

        toggleHintLayout(false)
        newGame()
    }

    fun toggleHintLayout(toggle: Boolean) {
        val hintLayout = findViewById<LinearLayout>(R.id.hintLayout)
        hintLayout?.visibility = if (toggle) View.VISIBLE else View.INVISIBLE
    }

    fun handleGuess() {
        val guessNumberInput = findViewById<EditText>(R.id.guessNumberInput)
        val hintText = findViewById<TextView>(R.id.hintValueText)
        val guessNumber = guessNumberInput?.text.toString()

        if(guessNumber.isEmpty()) return newGame()

        guessNumb++
        toggleHintLayout(true)

        if(guessNumber.toInt() < challengeNumb)
            hintText?.text = "The answer is higher ka!"
        else if(guessNumber.toInt() > challengeNumb)
            hintText?.text = "The answer is lower ka!"
        else
            correct()
    }

    fun newGame() {
        val guessNumberInput = findViewById<EditText>(R.id.guessNumberInput)
        toggleHintLayout(false)
        val resultText = findViewById<TextView>(R.id.resultText)
        resultText.text = "Try it!"
        guessNumberInput?.text?.clear()
        challengeNumb = (1 until 1000).random()
        updateAnswerHint()
        guessNumb = 0
    }

    fun correct() {
        val resultText = findViewById<TextView>(R.id.resultText)
        resultText.text = "You win!"
        toggleHintLayout(false)

    }

    fun updateAnswerHint() {
        val answerText = findViewById<TextView>(R.id.answerHint)
        answerText?.text = challengeNumb.toString()
    }

}
