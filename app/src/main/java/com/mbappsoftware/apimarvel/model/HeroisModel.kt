package com.mbappsoftware.apimarvel.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

class HeroisModel : Serializable {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("thumbnail")
    var thumbnail: ThumbnailModel = ThumbnailModel()


}