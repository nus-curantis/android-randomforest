package com.nus.curantis

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.nus.curantis.classifier.DataClassifier

class MainActivity : AppCompatActivity() {

    private val REQUEST_ENABLE_BLUETOOTH = 1
    private var bluetoothAdapter: BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        if (bluetoothAdapter?.isEnabled == false) {
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH)
        }
    }

    fun onClickGenerate(view: View) {

        val classifier = DataClassifier.loadModel(applicationContext, "randomforest")

        val data = DataClassifier.getData()

        val value = DataClassifier.classifyData(classifier, data).toInt()

        val activity = DataClassifier.getActivity(value)

        // Display action
        val textView = findViewById<TextView>(R.id.textview_main_humanaction)
        textView.text = resources.getString(activity)

    }

    fun onClickSensorApp(view: View) {

        val launchIntent = packageManager.getLaunchIntentForPackage("pl.projektorion.krzysztof.blesensortag")

        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(launchIntent)
        }
    }

    fun onClickShowPairedDevice(view: View) {

        val pairedDevices = bluetoothAdapter?.bondedDevices

        val listOfDevices = ArrayList<String>()

        pairedDevices?.forEach { bt -> listOfDevices.add(bt.name) }

        val listView = findViewById<ListView>(R.id.list_main_bluetoothdevices)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfDevices)

        listView?.adapter = adapter
    }

    fun onClickScanDevices(view: View) {

        val intent = Intent(applicationContext, ScanBluetoothDevicesActivity::class.java)
        startActivity(intent)
    }

}
