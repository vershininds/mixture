package com.vershininds.mixture.sample.presentation.modules.details.viewmodel

import android.os.Parcel
import com.vershininds.mixture.helper.ParcelableExt
import com.vershininds.mixture.helper.readTypedObjectCompat
import com.vershininds.mixture.helper.writeTypedObjectCompat
import com.vershininds.mixture.sample.data.SampleObject

class DetailsViewData(val sampleObject: SampleObject) : ParcelableExt {

    var error: String? = null

    companion object {
        @JvmField
        val CREATOR = ParcelableExt.parcelableCreator(::DetailsViewData)
    }

    private constructor(p: Parcel) : this(
            sampleObject = p.readTypedObjectCompat(SampleObject.CREATOR)!!
    ) {
        error = p.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedObjectCompat(sampleObject, flags)
        writeString(error)
    }
}