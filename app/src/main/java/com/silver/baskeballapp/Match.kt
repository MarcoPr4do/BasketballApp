package com.silver.baskeballapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Match (val pointLocal: Int, val pointVisitor: Int, val result: String, val marker: String, val image: Int) : Parcelable