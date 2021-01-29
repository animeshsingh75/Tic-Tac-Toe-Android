package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var board: Array<Array<Button>>
    var PLAYER = true
    var TURN_COUNT = 0
    var boardStatus = Array(3) { IntArray(3) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val resetBtn = findViewById<Button>(R.id.resetBtn)

        board = arrayOf(
            arrayOf(button, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )
        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()
        resetBtn.setOnClickListener {
            TURN_COUNT = 0
            PLAYER = true
            updateDisplay("Player X Turn")
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.button2 -> {
                updateValue(row = 0, col = 1, player = PLAYER)
            }
            R.id.button3 -> {
                updateValue(row = 0, col = 2, player = PLAYER)
            }
            R.id.button4 -> {
                updateValue(row = 1, col = 0, player = PLAYER)
            }
            R.id.button5 -> {
                updateValue(row = 1, col = 1, player = PLAYER)
            }
            R.id.button6 -> {
                updateValue(row = 1, col = 2, player = PLAYER)
            }
            R.id.button7 -> {
                updateValue(row = 2, col = 0, player = PLAYER)

            }
            R.id.button8 -> {
                updateValue(row = 2, col = 1, player = PLAYER)
            }
            R.id.button9 -> {
                updateValue(row = 2, col = 2, player = PLAYER)
            }
        }

        TURN_COUNT++
        PLAYER = !PLAYER
        if (PLAYER) {
            updateDisplay("Player X Turn")
        } else {
            updateDisplay("Player O Turn")
        }
        if (TURN_COUNT == 9) {
            updateDisplay("Game Draw")
        }
        checkWinner()

    }

    private fun checkWinner() {
        //Row Check
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][1]==boardStatus[i][2] && boardStatus[i][0]!=-1){
                if(boardStatus[i][0]==0){
                    updateDisplay("Player O Won")
                    break
                }else{
                    updateDisplay("Player X Won")
                    break
                }
            }
        }
        //Column Check
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[1][i]==boardStatus[2][i] && boardStatus[0][i]!=-1){
                if(boardStatus[0][i]==0){
                    updateDisplay("Player O Won")
                    break
                }else{
                    updateDisplay("Player X Won")
                    break
                }
            }
        }
        //Diagonal 1 Check
        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2] && boardStatus[0][0]!=-1){
            if(boardStatus[0][0]==0){
                updateDisplay("Player O Won")
            }else{
                updateDisplay("Player X Won")
            }
        }
        //Diagonal 2 Check
        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[0][2]==boardStatus[2][0] && boardStatus[2][0]!=-1){
            if(boardStatus[0][2]==0){
                updateDisplay("Player O Won")
            }else{
                updateDisplay("Player X Won")
            }
        }
    }

    private fun disableButton() {
        for (i in board) {
            for (button in i) {
                button.isEnabled=false
            }
        }
    }

    private fun updateDisplay(text: String) {
        val displayTv = findViewById<TextView>(R.id.displayTv)
        displayTv.text = text
        if(text.contains("Won")){
            disableButton()
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }
}