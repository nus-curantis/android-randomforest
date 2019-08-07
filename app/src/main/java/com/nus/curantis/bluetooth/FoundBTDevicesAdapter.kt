package com.nus.curantis.bluetooth

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.nus.curantis.R

import java.util.ArrayList

class FoundBTDevicesAdapter(context: Context, private val arrayFoundDevices: ArrayList<BluetoothObject>) :
    ArrayAdapter<BluetoothObject>(context, R.layout.activity_scan_bluetooth_devices, arrayFoundDevices) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val bluetoothObject = arrayFoundDevices[position]

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val rowView = inflater.inflate(R.layout.activity_scan_bluetooth_devices, parent, false)

        val bluetoothName = rowView.findViewById(R.id.textview_bt_scan_name) as TextView
        val bluetoothAddress = rowView.findViewById(R.id.textview_bt_scan_address) as TextView
        val bluetoothState = rowView.findViewById(R.id.textview_bt_scan_state) as TextView
        val bluetoothUuid = rowView.findViewById(R.id.textview_bt_scan_uuid) as TextView
        val bluetoothSignalStrength = rowView.findViewById(R.id.textview_bt_scan_signal_strength) as TextView

        bluetoothName.text = bluetoothObject.bluetoothName.toString()
        bluetoothAddress.text = bluetoothObject.bluetoothAddress.toString()
        bluetoothState.text = bluetoothObject.bluetoothState.toString()
        bluetoothSignalStrength.text = bluetoothObject.bluetoothRssi.toString()

        val uuid = bluetoothObject.bluetoothUuids
        if (uuid != null)
            bluetoothUuid.text = uuid[0].toString()

        return rowView
    }
}
