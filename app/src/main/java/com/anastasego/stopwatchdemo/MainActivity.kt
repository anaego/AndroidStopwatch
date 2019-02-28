package com.anastasego.stopwatchdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.widget.TextView
import android.text.style.RelativeSizeSpan



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var stopwatchResultString = "00:00:000"
        val spannableString = SpannableString(stopwatchResultString)
        spannableString.setSpan(RelativeSizeSpan(0.2f), 5, 9, 0)
        val stopwatchTextView = findViewById<TextView>(R.id.stopwatchTextView)
        stopwatchTextView.setText(spannableString)
    }
}
