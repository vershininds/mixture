package com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel

import android.os.Parcel
import com.vershininds.mixture.helper.ParcelableExt
import com.vershininds.mixture.sample.data.SampleObject

class CatalogViewData() : ParcelableExt {

    var sampleObjectList: List<SampleObject> = emptyList()
    var error: String? = null

    companion object {
        @JvmField
        val CREATOR = ParcelableExt.parcelableCreator(::CatalogViewData)
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