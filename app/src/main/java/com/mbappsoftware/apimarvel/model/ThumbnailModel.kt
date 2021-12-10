package com.mbappsoftware.apimarvel.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ThumbnailModel: Serializable {

    @SerializedName("path")
    var path: String = ""

    @SerializedName("extension")
    var extension: String = ""
}