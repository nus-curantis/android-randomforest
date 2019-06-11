package com.example.curantis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun generateAction() {

        // Generate model
        ModelGenerator.trainClassifier("something.csv", "classifierSave.model")

        // Display action
        val textView = findViewById<TextView>(R.id.textview_main_humanaction)
        textView.text = getString(R.string.all_placeholder)

    }

}
