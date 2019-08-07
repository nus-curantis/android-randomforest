package com.nus.curantis.bluetooth

import android.os.Parcel
import android.os.ParcelUuid
import android.os.Parcelable

class BluetoothObject : Parcelable {

    var bluetoothName: String? = null
    var bluetoothAddress: String? = null
    var bluetoothState: Int = 0
    var bluetoothUuids: Array<ParcelUuid>? = null
    var bluetoothRssi: Int = 0

    constructor()

    constructor(`in`: Parcel) : super() {
        readFromParcel(`in`)
    }

    private fun readFromParcel(`in`: Parcel) {
        bluetoothName = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeString(bluetoothName)
    }

    companion object {

        @JvmField val CREATOR: Parcelable.Creator<BluetoothObject> = object : Parcelable.Creator<BluetoothObject> {

            override fun createFromParcel(`in`: Parcel): BluetoothObject {

                return BluetoothObject(`in`)
            }

            override fun newArray(size: Int): Array<BluetoothObject?> {

                return arrayOfNulls(size)
            }
        }
    }
}
