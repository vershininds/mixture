package com.vershininds.mixture.sample.presentation.modules.catalog.viewmodel

import android.os.Parcel
import com.vershininds.mixture.helper.ParcelableExt
import com.vershininds.mixture.helper.readEnum
import com.vershininds.mixture.helper.writeEnum
import com.vershininds.mixture.sample.data.SampleObject
import com.vershininds.mixture.viewmodel.ViewState

class CatalogViewData() : ParcelableExt {

    var viewState: ViewState = ViewState.LOADING
    var sampleObjectList: List<SampleObject> = emptyList()
    var error: String? = null

    companion object {
        @JvmField
        val CREATOR = ParcelableExt.parcelableCreator(::CatalogViewData)
    }

    private constructor(p: Parcel) : this() {
        viewState = p.readEnum<ViewState>() ?: ViewState.LOADING
        sampleObjectList = p.createTypedArrayList(SampleObject.CREATOR)!!
        error = p.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeEnum(viewState)
        writeTypedList(sampleObjectList)
        writeString(error)
    }
}