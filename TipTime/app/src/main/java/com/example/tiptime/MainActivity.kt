package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.tiptime.databinding.ActivityMainBinding
import java.lang.Exception
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val currencyFormatter: NumberFormat = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        if (cost == null || cost <= 0) {
            binding.tipResult.text = "Please enter valid number!"
            return
        }
        val tipPercent = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.amazing -> 0.20
            R.id.good -> 0.18
            else -> 0.15
        }
        var tip = tipPercent * cost
        if(binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = currencyFormatter.format(tip)
        binding.tipResult.text = getString(R.string.tip_result, formattedTip)
    }
}