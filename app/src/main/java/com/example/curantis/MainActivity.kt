package com.example.curatis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun generateAction(view: View) {

        // Retrieve feature input
        // val editText = findViewById<EditText>(R.id.edittext_main_feature)
        // val featureToAnalyze = editText.toString()

        // Display action
        val textView = findViewById<TextView>(R.id.textview_main_humanaction)
        textView.text = getString(R.string.all_placeholder)
    }

}
