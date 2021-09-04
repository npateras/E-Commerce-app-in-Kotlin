package com.unipi.p17172.emarket.models

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * A data model class with required fields.
 */
@Keep
@Parcelize
@IgnoreExtraProperties
data class Product(
    val addedByUser: String = "",
    val category: String = "",
    @ServerTimestamp
    val dateAdded: Date = Date(),
    val description: String = "",
    val iconUrl: String = "",
    val name: String = "",
    val popularity: Popularity = Popularity(),
    val price: Double = 0.00,
    val sale: Float = 0f,
    val stock: Int = 0,
    val weight: Int = 0,
    val weightUnit: String = "",
    var id: String = "",
) : Parcelable
