package com.mbappsoftware.apimarvel.client

import android.graphics.drawable.Drawable
import com.mbappsoftware.apimarvel.constants.MarvelConstants
import com.mbappsoftware.apimarvel.constants.MarvelConstants.CHAVES.PRIVATE_KEY
import com.mbappsoftware.apimarvel.constants.MarvelConstants.CHAVES.PUBLIC_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MarvelClient private constructor(){

    companion object{
        private lateinit var retrofit: Retrofit

        private fun getRetroftInstance(): Retrofit {


            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(MarvelConstants.CHAVES.URL_BASE)
                    //.client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

        /*fun addHeader(token: String, personKey: String){
            this.personKey = personKey
            this.tokenKey = token

        }*/

        fun <S> createService(serviceClass: Class<S>): S{
            return getRetroftInstance().create(serviceClass)
        }

    }
}