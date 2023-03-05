package com.cn333.guessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var answerNum: Int = 0
    var guessNum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val checkButton = findViewById<Button>(R.id.guessNumberButton)
        val playAgainButton = findViewById<Button>(R.id.newGameButton)

        checkButton.setOnClickListener {
            checkResult()
        }

        playAgainButton.setOnClickListener {
            playAgain()
        }

        toggleHintLayout(false)
        playAgain()
    }

    fun toggleHintLayout(toggle: Boolean) {
        val hintLayout = findViewById<LinearLayout>(R.id.hintLayout)
        hintLayout?.visibility = if (toggle) View.VISIBLE else View.INVISIBLE
    }

    fun checkResult() {
        val numberInput = findViewById<EditText>(R.id.Input)
        val hint = findViewById<TextView>(R.id.hintTelling)
        val randomNumber = numberInput?.text.toString()

        if(randomNumber.isEmpty()) return playAgain()

        guessNum++
        toggleHintLayout(true)

        if(randomNumber.toInt() < answerNum)
            hint?.text = "The answer is higher. "
        else if(randomNumber.toInt() > answerNum)
            hint?.text = "The answer is lower. "
        else
            correctAnswer()
    }

    fun playAgain() {
        val numberInput = findViewById<EditText>(R.id.Input)
        toggleHintLayout(false)
        val resultText = findViewById<TextView>(R.id.resultText)
        resultText.text = "Try it!"
        numberInput?.text?.clear()
        answerNum = (1 until 1000).random()
        updateAnswerHint()
        guessNum = 0
    }

    fun correctAnswer() {
        val resultText = findViewById<TextView>(R.id.resultText)
        resultText.text = "CORRECT! " + guessNum.toString() + " TIMES. "
        toggleHintLayout(false)

    }

    fun updateAnswerHint() {
        val answerText = findViewById<TextView>(R.id.answerHint)
        // ไว้สำหรับเช็คคำตอบ
        //answerText?.text = answerNum.toString()
    }

}