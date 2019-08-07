package com.nus.curantis

import android.app.ListActivity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.nus.curantis.bluetooth.BluetoothObject
import com.nus.curantis.bluetooth.FoundBTDevicesAdapter

import java.util.ArrayList

class ScanBluetoothDevicesActivity : ListActivity() {
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var arrayOfFoundBTDevices: ArrayList<BluetoothObject>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        displayListOfFoundDevices()
    }

    private fun displayListOfFoundDevices() {
        arrayOfFoundBTDevices = ArrayList()

        // start looking for bluetooth devices
        bluetoothAdapter!!.startDiscovery()

        // Discover new devices
        // Create a BroadcastReceiver for ACTION_FOUND
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {

                val action = intent.action

                // When discovery finds a device
                if (BluetoothDevice.ACTION_FOUND == action) {

                    // Get the bluetoothDevice object from the Intent
                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)

                    // Get the "RSSI" to get the signal strength as integer,
                    // but should be displayed in "dBm" units
                    val rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, java.lang.Short.MIN_VALUE).toInt()

                    // Create the device object and add it to the arrayList of devices
                    val bluetoothObject = BluetoothObject()
                    bluetoothObject.bluetoothName = device.name
                    bluetoothObject.bluetoothAddress = device.address
                    bluetoothObject.bluetoothState = device.bondState
                    bluetoothObject.bluetoothUuids = device.uuids
                    bluetoothObject.bluetoothRssi = rssi

                    arrayOfFoundBTDevices!!.add(bluetoothObject)

                    // Pass context and data to the custom adapter
                    val adapter = FoundBTDevicesAdapter(applicationContext, arrayOfFoundBTDevices!!)
                    listAdapter = adapter
                }
            }
        }
        // Register the BroadcastReceiver
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(receiver, filter)
    }

    override fun onPause() {

        super.onPause()
        bluetoothAdapter!!.cancelDiscovery()
    }
}