package com.mbappsoftware.apimarvel.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.mbappsoftware.apimarvel.constants.MarvelConstants
import com.mbappsoftware.apimarvel.model.BodyModel
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    fun allPreson(
        @Query("offset") offset: Int,
        @Query("orderBy") orderBy: String = "name",
        @Query("limit") limit: Int = 4,
        @Query("ts") ts: String? = MarvelConstants.funcoes.getTimeStamp(),
        @Query("apikey") apikey: String = MarvelConstants.CHAVES.PUBLIC_KEY,
        @Query("hash") hash: String
        ): Call<BodyModel>

    @GET("characters")
    fun buscaPersonagem(
        @Query("nameStartsWith") nameStartsWith: String?,
        @Query("orderBy") orderBy: String = "name",
        @Query("limit") limit: Int = 4,
        @Query("ts") ts: String? = MarvelConstants.funcoes.getTimeStamp(),
        @Query("apikey") apikey: String = MarvelConstants.CHAVES.PUBLIC_KEY,
        @Query("hash") hash: String
    ): Call<BodyModel>
}