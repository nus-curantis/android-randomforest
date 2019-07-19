package com.example.curantis

import android.content.Context

import weka.classifiers.Classifier
import weka.core.Instances
import weka.core.SerializationHelper
import weka.core.converters.CSVLoader

object ModelGenerator {

    fun loadTrainingData(context: Context, fileName: String): Instances {

        val csvLoader = CSVLoader()

        val resourceId = context.resources.getIdentifier(fileName, "raw", context.packageName)
        val inputStream = context.resources.openRawResource(resourceId)

        csvLoader.setSource(inputStream)

        val trainingData = csvLoader.dataSet
        trainingData.setClassIndex(0)

        return trainingData
    }

    fun saveModel(classifier: Classifier, filePath: String) {

        SerializationHelper.write(filePath, classifier)
    }

}