package com.example.tms_android_homework.note.data

import android.os.Parcel
import android.os.Parcelable

data class Note(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty()
    )

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(source: Parcel): Note {
            return Note(source)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0;
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageUrl);
    }

}