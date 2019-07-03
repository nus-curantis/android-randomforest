package com.example.curantis

import weka.classifiers.Classifier
import weka.core.Attribute
import weka.core.DenseInstance
import weka.core.Instances
import weka.core.SerializationHelper

object DataClassifier {

    fun loadModel(filePath: String): Classifier{
        return (SerializationHelper.read(filePath) as Classifier)
    }

    fun getData(): Instances {
/*
        // Row 2 Label 5
        val xMean = -0.0359242756
        val yMean = -0.0484093559
        val zMean = 0.1241743295
        val xStd = 0.0207383921
        val yStd = 0.0200620169
        val zStd = 0.0111291944
        val xyCorr = -0.61619785
        val xzCorr = 0.1015040842
        val yzCorr = 0.2711583038
        val xEnergy = 0.1027181954
        val yEnergy = 0.1736098818
        val zEnergy = 1.0560061627
        val pitch = -0.1297794478
        val roll = -0.1615366741
        val yaw = 0.404065586
*/

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
/*
        // Row 3 Label 5
        val xMean = 0.0103641086
        val yMean = -0.0302412245
        val zMean = 0.1353251132
        val xStd = 0.003878063
        val yStd = 0.0074936862
        val zStd = 0.0082458532
        val xyCorr = -0.1366039348
        val xzCorr = -0.1593848051
        val yzCorr = 0.4323666662
        val xEnergy = 0.0078567852
        val yEnergy = 0.0643214771
        val zEnergy = 1.2515594742
        val pitch = 0.0319606774
        val roll = -0.0790641114
        val yaw = 0.764912862

        // Row 78 Label 4
        val xMean = 0.0114220514
        val yMean = -0.0423821345
        val zMean = 0.2475602894
        val xStd = 0.020405485
        val yStd = 0.02853998
        val zStd = 0.0238288489
        val xyCorr = -0.421263661
        val xzCorr = 0.2432958802
        val yzCorr = 0.2067590397
        val xEnergy = 0.0231311417
        val yEnergy = 0.1506014727
        val zEnergy = 4.2000736728
        val pitch = 0.0252107941
        val roll = -0.0830896054
        val yaw = -0.0830896054*/

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
}