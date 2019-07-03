package com.example.curantis

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import weka.classifiers.trees.RandomForest
import weka.core.SerializationHelper

@RunWith(AndroidJUnit4::class)
class DataClassifierTest {

    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadModel_correctFile_loadSuccessfully() {
        val context = mainActivityTestRule.activity.baseContext
        val fileName = context.filesDir.absolutePath + "emptyclassifier.model"

        SerializationHelper.write(fileName, RandomForest())

        Assert.assertNotNull(DataClassifier.loadModel(fileName))
    }
}
