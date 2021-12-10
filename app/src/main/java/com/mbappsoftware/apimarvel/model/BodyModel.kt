package com.mbappsoftware.apimarvel.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BodyModel {

    @SerializedName("data")
    var itemModel: ItemModel = ItemModel()
}