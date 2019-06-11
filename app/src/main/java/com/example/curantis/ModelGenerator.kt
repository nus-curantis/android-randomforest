package com.example.curantis

import weka.classifiers.Classifier
import weka.classifiers.trees.RandomForest
import weka.core.Instances
import weka.core.SerializationHelper
import weka.core.converters.CSVLoader

import java.io.File
import java.io.IOException
import java.lang.Exception

object ModelGenerator{

    @Throws(Exception::class)
    fun trainClassifier(trainingDataPath: String, classifierSavePath: String) {

        val trainingData = loadTrainingData(trainingDataPath)

        val classifier = buildClassifier(trainingData)

        saveClassifier(classifierSavePath, classifier)
    }

    @Throws(IOException::class)
    private fun loadTrainingData(trainingDataPath: String): Instances {

        val csvLoader = CSVLoader()
        csvLoader.setSource(File(trainingDataPath))

        val trainingData = csvLoader.dataSet
        trainingData.setClassIndex(0)

        return trainingData
    }

    @Throws(Exception::class)
    private fun buildClassifier(trainingData: Instances): Classifier {

        val classifier = RandomForest()
        classifier.buildClassifier(trainingData)

        return classifier
    }

    @Throws(Exception::class)
    private fun saveClassifier(classifierSavePath: String, classifier: Classifier) {

        SerializationHelper.write(classifierSavePath, classifier)
    }
}
