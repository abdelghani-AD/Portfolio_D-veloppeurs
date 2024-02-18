package com.example.projectdeveloper.model

import android.os.Parcel
import android.os.Parcelable

//Parcelable est utilisé pour optimiser le processus de passage
// d'objets complexes entre les composants d'une application Android
data class Modele(var langage: String, var developper: String, var image: Int):Parcelable {
    //créer une instance de la classe à partir d'un objet Parcel. Parcel
    constructor(parcel: Parcel) : this(parcel.readString()!!, parcel.readString()!!, parcel.readInt())
    override fun describeContents(): Int {
        return 0
    }
    //écrire les propriétés de l'objet dans le Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(langage)
        parcel.writeString(developper)
        parcel.writeInt(image)
    }
    companion object CREATOR : Parcelable.Creator<Modele> {
        override fun createFromParcel(parcel: Parcel): Modele {
            return Modele(parcel)
        }
        override fun newArray(size: Int): Array<Modele?> {
            return arrayOfNulls(size)
        }
    }
}
