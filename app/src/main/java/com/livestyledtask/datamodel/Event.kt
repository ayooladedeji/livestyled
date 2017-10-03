package com.livestyledtask.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ayoola on 29/09/2017.
 */
@Parcelize
data class Event(
        val id: String,
        val name: String,
        val venueName: String,
        val startTime: String,
        val startDate: String,
        val imageUrl: String,
        val genre: String
) : Parcelable