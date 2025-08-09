package com.example.tms_android_homework.domain.models

import android.os.Parcel
import android.os.Parcelable

data class ItemTitleModel(
    val title: String
) : Parcelable {

    constructor(parcel: Parcel) : this (
        parcel.readString().orEmpty()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ItemTitleModel> {
        override fun createFromParcel(parcel: Parcel): ItemTitleModel {
            return ItemTitleModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemTitleModel?> {
            return arrayOfNulls(size)
        }
    }
}