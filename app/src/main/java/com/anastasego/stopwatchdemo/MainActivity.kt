package com.anastasego.stopwatchdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.widget.Button
import android.widget.TextView
import io.reactivex.schedulers.Schedulers
import java.lang.Math.floor


class MainActivity : AppCompatActivity() {

    val scheduler = Schedulers.computation()
    val stopwatch = Stopwatch.Impl(scheduler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val stopwatchTextView = findViewById<TextView>(R.id.stopwatchTextView)

        val stopwatchResultString = "00:00:000"
        val spannableString = SpannableString(stopwatchResultString)
        spannableString.setSpan(RelativeSizeSpan(0.2f), 5, 9, 0)
        stopwatchTextView.setText(spannableString)

        val startButton = findViewById<Button>(R.id.startbutton)
        val resetButton = findViewById<Button>(R.id.resetbutton)
        startButton.setOnClickListener{
            stopwatch.value.subscribe{value -> stopwatchTextView.setText(numberOfMinutesToString(value))}
            stopwatch.resume()
        }
        resetButton.setOnClickListener{
            stopwatch.pause()
        }
    }

    private fun numberOfMinutesToString(seconds: Long): SpannableString {
        val spannableString = SpannableString((floor((seconds / 60).toDouble())).toInt().toString()
                .padStart(2, '0') + ":" + (seconds % 60).toString().padStart(2, '0')
                + ":000")
        spannableString.setSpan(RelativeSizeSpan(0.2f), 5, 9, 0)
        return spannableString
    }

}
