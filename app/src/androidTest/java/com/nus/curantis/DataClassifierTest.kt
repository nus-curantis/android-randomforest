package com.nus.curantis

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.nus.curantis.classifier.DataClassifier

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DataClassifierTest {

    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadModel_correctFile_loadSuccessfully() {
        val context = mainActivityTestRule.activity.baseContext

        Assert.assertNotNull(DataClassifier.loadModel(context, "randomforest"))
    }
}
