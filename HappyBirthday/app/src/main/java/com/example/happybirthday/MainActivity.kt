package com.example.happybirthday
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main);

        val helloTextView: TextView = findViewById(R.id.happy_birthday);
        helloTextView.setText(R.string.happy_birthday);

        val signature: TextView = findViewById(R.id.signature);
        signature.setText(R.string.signature);
    }
}