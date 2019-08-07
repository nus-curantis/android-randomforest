package com.nus.curantis

import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nus.curantis.classifier.ModelGenerator

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import weka.classifiers.Classifier
import weka.classifiers.trees.RandomForest
import weka.core.Attribute
import weka.core.DenseInstance
import weka.core.Instances
import weka.core.SerializationHelper

@RunWith(AndroidJUnit4::class)
class ModelGeneratorTest {

    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadTrainingDataTest_correctCsv_loadSuccessfully() {
        val context = mainActivityTestRule.activity.baseContext

        val loadedTrainingData: Instances = ModelGenerator.loadTrainingData(context, "test")

        val labels = ArrayList<String>()
        labels.add("WorkingOnLaptop")

        val attributes = ArrayList<Attribute>(16)
        attributes.add(Attribute("label", labels))
        attributes.add(Attribute("x_mean"))
        attributes.add(Attribute("y_mean"))
        attributes.add(Attribute("z_mean"))
        attributes.add(Attribute("x_std"))
        attributes.add(Attribute("y_std"))
        attributes.add(Attribute("z_std"))
        attributes.add(Attribute("xy_corr"))
        attributes.add(Attribute("xz_corr"))
        attributes.add(Attribute("yz_corr"))
        attributes.add(Attribute("x_energy"))
        attributes.add(Attribute("y_energy"))
        attributes.add(Attribute("z_energy"))
        attributes.add(Attribute("pitch"))
        attributes.add(Attribute("roll"))
        attributes.add(Attribute("yaw"))

        val expectedTrainingData = Instances("stream", attributes, 0)

        val expectedInstanceValue = DoubleArray(expectedTrainingData.numAttributes())
        expectedInstanceValue[0] = 0.0
        expectedInstanceValue[1] = -0.035924276
        expectedInstanceValue[2] = -0.048409356
        expectedInstanceValue[3] = 0.12417433
        expectedInstanceValue[4] = 0.020738392
        expectedInstanceValue[5] = 0.020062017
        expectedInstanceValue[6] = 0.011129194
        expectedInstanceValue[7] = -0.61619785
        expectedInstanceValue[8] = 0.101504084
        expectedInstanceValue[9] = 0.271158304
        expectedInstanceValue[10] = 0.102718195
        expectedInstanceValue[11] = 0.173609882
        expectedInstanceValue[12] = 1.056006163
        expectedInstanceValue[13] = -0.129779448
        expectedInstanceValue[14] = -0.161536674
        expectedInstanceValue[15] = 0.404065586

        expectedTrainingData.add(DenseInstance(1.0, expectedInstanceValue))

        assertEquals("Instances not equal when loading training data!", expectedTrainingData.toString(), loadedTrainingData.toString())
    }

    @Test
    fun saveModel_newClassifier_saveSuccessfully() {

        val context = mainActivityTestRule.activity.baseContext
        val fileName = context.filesDir.absolutePath + "emptyclassifier.model"

        ModelGenerator.saveModel(RandomForest(), fileName)

        assertNotNull(SerializationHelper.read(fileName) as Classifier)
    }
}
