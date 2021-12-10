package com.mbappsoftware.apimarvel.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbappsoftware.apimarvel.api.APIListener
import com.mbappsoftware.apimarvel.model.BodyModel
import com.mbappsoftware.apimarvel.repository.MarvelRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val mMarvelRepository = MarvelRepository(application)

    private val mMarvelLoad = MutableLiveData<BodyModel>()
    var marvelLoad: LiveData<BodyModel> = mMarvelLoad

    private val mMarvelBusca = MutableLiveData<BodyModel>()
    var marvelBusca: LiveData<BodyModel> = mMarvelBusca

    var mTotal:Int = 0
    var mResultado:Int = 0

    var mList: MutableList<Int> = arrayListOf()

    fun load(offSet: Int){
        mMarvelRepository.load(offSet, object : APIListener<BodyModel>{
            override fun onSuccess(model: BodyModel) {
                mMarvelLoad.value = model
                Log.i("awdawd", " total -> " + mTotal + "\nresultado -> " + mResultado + "\n model -> " + model.toString())
            }

            override fun onFailure(str: String) {

            }

        })
    }

    fun buscaPersonagemNome(newText: String?) {
        mMarvelRepository.buscaPersonagemNome(newText, object : APIListener<BodyModel>{
            override fun onSuccess(model: BodyModel) {
                mMarvelBusca.value = model
            }

            override fun onFailure(str: String) {

            }

        })
    }

}