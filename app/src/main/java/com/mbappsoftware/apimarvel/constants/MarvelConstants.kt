package com.mbappsoftware.apimarvel.constants

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class MarvelConstants {

    object CHAVES {
        const val URL_BASE = "https://gateway.marvel.com/v1/public/"
        const val PUBLIC_KEY = "c530a63b9f836a3a51274fc153f382e0"
        const val PRIVATE_KEY = "95620e415e1c45a709921c8fdf9166177c3ee07e"
    }

    object HTTP {
        const val SUCCESS = 200
    }

    object funcoes{
        fun getTimeStamp(): String? {
            return DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now())

        }
    }

}