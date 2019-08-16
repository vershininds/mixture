package com.vershininds.mixture.sample.data

import android.os.Parcel
import com.vershininds.mixture.helper.ParcelableExt

data class SampleObject(var name: String, var description: String) : ParcelableExt {

    companion object {
        @JvmField
        val CREATOR = ParcelableExt.parcelableCreator(::SampleObject)
    }

    private constructor(p: Parcel) : this(
            name = p.readString()!!,
            description = p.readString()!!)

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(description)
    }

}