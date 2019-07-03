package com.example.curantis

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.TextView

import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val savedFilePath = this.filesDir.absolutePath + "classifier.model"

        if (!isFileExist(savedFilePath)) {
            ModelGenerator.createNewModel(applicationContext, savedFilePath)
        }

        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {

        val classifier = DataClassifier.loadModel(this.filesDir.absolutePath + "classifier.model")

        val data = DataClassifier.getData()

        val value = DataClassifier.classifyData(classifier, data)

        // Display action
        val textView = findViewById<TextView>(R.id.textview_main_humanaction)
        textView.text = value.toString()

    }

    private fun isFileExist(filePath: String): Boolean {

        return File(filePath).exists()
    }
}
