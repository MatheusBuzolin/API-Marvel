package com.mbappsoftware.apimarvel.model

import com.google.gson.annotations.SerializedName

class ItemModel {

    @SerializedName("limit")
    var limit: Int = 0

    @SerializedName("total")
    var total: Int = 0

    @SerializedName("count")
    var count: Int = 0

    @SerializedName("results")
    var results: List<HeroisModel> = listOf()


}