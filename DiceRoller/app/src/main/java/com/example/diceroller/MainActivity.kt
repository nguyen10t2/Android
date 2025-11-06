package com.example.diceroller

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private val dice = Dice(6)

    private val diceImages = intArrayOf(
        R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6,
    )

    private lateinit var resultText: TextView
    private lateinit var resultView1: ImageView
    private lateinit var resultView2: ImageView
    private lateinit var resultView3: ImageView
    private lateinit var rollButton: Button

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.result_text)

        resultView1 = findViewById(R.id.imageView1)
        resultView1.setImageResource(diceImages[0])

        resultView2 = findViewById(R.id.imageView2)
        resultView2.setImageResource(diceImages[2])

        resultView3 = findViewById(R.id.imageView3)
        resultView3.setImageResource(diceImages[3])

        rollButton = findViewById(R.id.button)

        rollButton.setOnClickListener {
            rollDiceWithAnimation()
        }
    }

    private fun rollDiceWithAnimation() {
        resultText.setText(R.string.rolling)
        rollButton.isEnabled = false

        val animationDuration = 1500L
        val updateInterval = 50L
        var elapsedTime = 0L

        val runnable = object : Runnable {
            override fun run() {
                val listRoll = listOf(dice.roll(), dice.roll(), dice.roll())
                resultView1.setImageResource(diceImages[listRoll[0] - 1])
                resultView2.setImageResource(diceImages[listRoll[1] - 1])
                resultView3.setImageResource(diceImages[listRoll[2] - 1])

                if (elapsedTime < animationDuration) {
                    elapsedTime += updateInterval
                    handler.postDelayed(this, updateInterval)
                } else {
                    when (listRoll.sum()) {
                        in 3..10 -> resultText.setText(R.string.small)
                        else -> resultText.setText(R.string.big)
                    }
                    rollButton.isEnabled = true
                }
            }
        }

        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    class Dice(val numSides: Int) {
        fun roll(): Int {
            return (1..numSides).random()
        }
    }
}