package com.mbappsoftware.apimarvel.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.mbappsoftware.apimarvel.api.APIListener
import com.mbappsoftware.apimarvel.api.MarvelService
import com.mbappsoftware.apimarvel.client.MarvelClient
import com.mbappsoftware.apimarvel.constants.MarvelConstants
import com.mbappsoftware.apimarvel.model.BodyModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest

class MarvelRepository(val context: Context) {

    private val mRomete = MarvelClient.createService(MarvelService::class.java)

    fun load(offSet: Int, listener: APIListener<BodyModel>){
        val call: Call<BodyModel> = mRomete.allPreson(hash = getHash(), offset = offSet)
        call.enqueue(object : Callback<BodyModel>{
            override fun onResponse(call: Call<BodyModel>, response: Response<BodyModel>) {
                Log.i("loadfsds", " -> ${response.code()}")
                Log.i("loadfsds", " -> ${call.request().url.toString()}")

                if (response.code() == 200){
                    response.body()?.let {
                        Log.i("rgefafef", "ENTRO IF -> " +  listener.onSuccess(it) )
                        listener.onSuccess(it)
                    }
                    Log.i("rgefafef", "ENTRO IFIF -> " )

                }else{
                    val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    Log.i("rgefafef", "ELSE -> ${validation}")
                    listener.onFailure(validation)

                }
            }

            override fun onFailure(call: Call<BodyModel>, t: Throwable) {
                listener.onFailure("Erro ao carregar dados!")
            }

        })
    }

    fun buscaPersonagemNome(newText: String?, listener: APIListener<BodyModel>) {
        val call: Call<BodyModel> = mRomete.buscaPersonagem(hash = getHash(), nameStartsWith = newText)
        call.enqueue(object : Callback<BodyModel>{
            override fun onResponse(call: Call<BodyModel>, response: Response<BodyModel>) {
                Log.i("buscaPersonagemNomefsds", " -> ${response.code()}")
                Log.i("buscaPersonagemNomefsds", " -> ${call.request().url.toString()}")

                if (response.code() != MarvelConstants.HTTP.SUCCESS){
                    val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    Log.i("rgefafef", " -> ${validation}")
                    listener.onFailure(validation)
                }else{
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<BodyModel>, t: Throwable) {
                listener.onFailure("Erro ao carregar dados!")
            }

        })
    }

    @SuppressLint("NewApi")
    fun getHash() : String{
        val timestamp = MarvelConstants.funcoes.getTimeStamp()
        val privateApiKey = MarvelConstants.CHAVES.PRIVATE_KEY
        val publicApiKey = MarvelConstants.CHAVES.PUBLIC_KEY
        val hash = createHash(timestamp+privateApiKey+publicApiKey)
        Log.i("DFDFFF","https://gateway.marvel.com/v1/public/characters?ts=$timestamp&apikey=$publicApiKey&hash=$hash")
        Log.i("DFDFFF", " -> ${hash}")
        return hash

    }

    private fun createHash(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}