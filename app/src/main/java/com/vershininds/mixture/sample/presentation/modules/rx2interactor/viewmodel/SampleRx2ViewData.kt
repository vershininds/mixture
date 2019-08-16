package com.vershininds.mixture.sample.presentation.modules.rx2interactor.viewmodel

import android.os.Parcel
import com.vershininds.mixture.helper.ParcelableExt
import com.vershininds.mixture.sample.data.SampleObject

class SampleRx2ViewData() : ParcelableExt {

    var sampleObjectList: List<SampleObject> = emptyList()
    var error: String? = null

    companion object {
        @JvmField
        val CREATOR = ParcelableExt.parcelableCreator(::SampleRx2ViewData)
    }

    private constructor(p: Parcel) : this() {
        sampleObjectList = p.createTypedArrayList(SampleObject.CREATOR)!!
        error = p.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(sampleObjectList)
        writeString(error)
    }
}