package com.example.tms_android_homework.domain.models

import android.os.Parcel
import android.os.Parcelable

class ItemDetailModel(
    val title: String,
    val description: String
) : Parcelable {

    constructor(parcel: Parcel) : this (
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ItemDetailModel> {
        override fun createFromParcel(parcel: Parcel): ItemDetailModel {
            return ItemDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemDetailModel?> {
            return arrayOfNulls(size)
        }
    }
}