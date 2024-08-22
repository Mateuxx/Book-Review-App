package com.example.bookappreview.model

import android.os.Parcel
import java.util.UUID
import android.os.Parcelable

data class Livro(
    val id: String = UUID.randomUUID().toString(),
    var title: String?,
    var subtitle: String?,
    var publisher: String?,
    val imagem: String? = null, // url
    var description: String?,
    var pageCount: Int,
    var year: String?,
    var autor: String? = null
//    var authors: ArrayList<String>,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.run { readString() },
        parcel.readString(),
        parcel.run { readInt() },
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(subtitle)
        parcel.writeString(publisher)
        parcel.writeString(imagem)
        parcel.writeString(description)
        parcel.writeInt(pageCount)
        parcel.writeString(year)
        parcel.writeString(autor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Livro> {
        override fun createFromParcel(parcel: Parcel): Livro {
            return Livro(parcel)
        }

        override fun newArray(size: Int): Array<Livro?> {
            return arrayOfNulls(size)
        }
    }
}
