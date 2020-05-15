package com.towm1204.dotify.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist(
    val name: String,
    val smallImageURL: String?,
    val largeImageURL: String?
): Parcelable
