package com.example.curantis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.TextView

import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    fun onClickGenerate() {

        val classifier = DataClassifier.loadModel(applicationContext, "randomforest")

        val data = DataClassifier.getData()

        val value = DataClassifier.classifyData(classifier, data)

        // Display action
        val textView = findViewById<TextView>(R.id.textview_main_humanaction)
        textView.text = value.toString()

    }

    fun onClickSensorApp (){

        val launchIntent = packageManager.getLaunchIntentForPackage("pl.projektorion.krzysztof.blesensortag")

        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(launchIntent)
        }
    }
}
