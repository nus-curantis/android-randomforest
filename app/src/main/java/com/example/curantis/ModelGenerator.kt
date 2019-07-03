package com.example.curantis

import android.content.Context

import weka.classifiers.Classifier
import weka.classifiers.trees.RandomForest
import weka.core.Instances
import weka.core.SerializationHelper
import weka.core.converters.CSVLoader

object ModelGenerator {

    fun createNewModel(context: Context, filePath: String) {

        val fileName = "feature"

        val trainingData = loadTrainingData(context, fileName)

        val newClassifier = buildModel(trainingData)

        saveModel(newClassifier, filePath)
    }

    fun loadTrainingData(context: Context, fileName: String): Instances {

        val csvLoader = CSVLoader()

        val resourceId = context.resources.getIdentifier(fileName, "raw", context.packageName)
        val inputStream = context.resources.openRawResource(resourceId)

        csvLoader.setSource(inputStream)

        val trainingData = csvLoader.dataSet
        trainingData.setClassIndex(0)

        return trainingData
    }

    fun buildModel(trainingData: Instances): Classifier {

        val classifier = RandomForest()
        classifier.seed = 31
        classifier.numTrees = 160
        classifier.buildClassifier(trainingData)

        return classifier
    }

    fun saveModel(classifier: Classifier, filePath: String) {

        SerializationHelper.write(filePath, classifier)
    }

}