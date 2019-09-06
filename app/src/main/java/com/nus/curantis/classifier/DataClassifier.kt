package com.nus.curantis.classifier

import android.content.Context
import com.nus.curantis.R

import weka.classifiers.Classifier
import weka.classifiers.trees.RandomForest
import weka.core.Attribute
import weka.core.DenseInstance
import weka.core.Instances
import weka.core.SerializationHelper
import weka.core.converters.ConverterUtils

object DataClassifier {

    fun loadModel(context: Context, fileName: String): Classifier{

        val resourceId = context.resources.getIdentifier(fileName, "raw", context.packageName)
        val inputStream = context.resources.openRawResource(resourceId)

        return SerializationHelper.read(inputStream) as RandomForest
    }

    fun loadData(context: Context, fileName: String): Instances {

        val resourceId = context.resources.getIdentifier(fileName, "raw", context.packageName)
        val inputStream = context.resources.openRawResource(resourceId)

        val source = ConverterUtils.DataSource(inputStream)
        val data = source.dataSet
        data.setClassIndex(0)

        return data
    }

    fun classifyData(data: Instances, classifier: Classifier): Double {

        var results = 0.0

        for (instance in data) {
            results += classifier.classifyInstance(instance)
        }

        return results

    }

    fun getData(): Instances {


        val xMean = 0.246037802
        val yMean = -0.050342136
        val zMean = 0.021387749
        val xStd = 0.039795003
        val yStd = 0.020056196
        val zStd = 0.022621295
        val xyCorr = 0.142001582
        val xzCorr = -0.58137519
        val yzCorr = -0.137526038
        val xEnergy = 4.183126519
        val yEnergy = 0.1866373
        val zEnergy = 0.048656738
        val pitch = 0.965729827
        val roll = -0.097042927
        val yaw = 0.048309932

        /*
        // Row 64 Label 5
        val xMean = 0.0181867812
        val yMean = -0.021516588
        val zMean = 0.2546997069
        val xStd = 0.0073890709
        val yStd = 0.0055920677
        val zStd = 0.0079460782
        val xyCorr = 0.0584377183
        val xzCorr = 0.3224723478
        val yzCorr = 0.2651129497
        val xEnergy = 0.0244335656
        val yEnergy = 0.0326679942
        val zEnergy = 4.4271064745
        val pitch = 0.0376445467
        val roll = -0.0411658387
        val yaw = 1.3479986243
*/
        val attributes = ArrayList<Attribute>(16)

        val labels = ArrayList<String>()
        labels.add("Walking")
        labels.add("Running")
        labels.add("CommuteInBus")
        labels.add("EatingUsingForkAndSpoon")
        labels.add("UsingMobilePhone")
        labels.add("WorkingOnLaptop")
        labels.add("Sitting")
        labels.add("WashingHands")
        labels.add("EatingWithHands")
        labels.add("ConversingWhileSitting")
        labels.add("Elevator")
        labels.add("OpeningDoor")
        labels.add("Standing")
        labels.add("ClimbingUpstairs")
        labels.add("Jogging")
        labels.add("VideoChatWhileSitting")
        labels.add("Sleeping")
        labels.add("WalkingWithHandInPocket")
        labels.add("Writing")

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

        val rawData = Instances("stream", attributes, 0)
        val instanceValue = DoubleArray(rawData.numAttributes())
        instanceValue[0] = 0.0

        instanceValue[1] = xMean
        instanceValue[2] = yMean
        instanceValue[3] = zMean
        instanceValue[4] = xStd
        instanceValue[5] = yStd
        instanceValue[6] = zStd
        instanceValue[7] = xyCorr
        instanceValue[8] = xzCorr
        instanceValue[9] = yzCorr
        instanceValue[10] = xEnergy
        instanceValue[11] = yEnergy
        instanceValue[12] = zEnergy
        instanceValue[13] = pitch
        instanceValue[14] = roll
        instanceValue[15] = yaw

        rawData.add(DenseInstance(1.0, instanceValue))
        rawData.setClassIndex(0)

        return rawData
    }

    fun classifyData(classifier: Classifier, data: Instances): Double {
        return classifier.classifyInstance(data.instance(0))
    }

    fun getActivity(value: Int) : Int {
        when(value) {
            0 -> return R.string.main_walkingactivity
            1 -> return R.string.main_runningactivity
            2 -> return R.string.main_commuteactivity
            3 -> return R.string.main_eatingforkandspoonactivity
            4 -> return R.string.main_mobilephoneactivity
            5 -> return R.string.main_workingonlaptopactivity
            6 -> return R.string.main_sittingactivity
            7 -> return R.string.main_washinghandsactivity
            8 -> return R.string.main_eatinghandsactivity
            9 -> return R.string.main_conversingactivity
            10 -> return R.string.main_elevatoractivity
            11 -> return R.string.main_openingdooractivity
            12 -> return R.string.main_standingactivity
            13 -> return R.string.main_climbingupstairsactivity
            14 -> return R.string.main_joggingactivity
            15 -> return R.string.main_videochatactivity
            else -> {
                return R.string.main_untaggedactivity
            }
        }
    }
}