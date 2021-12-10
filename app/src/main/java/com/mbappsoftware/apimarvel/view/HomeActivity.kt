package com.mbappsoftware.apimarvel.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbappsoftware.apimarvel.R
import com.mbappsoftware.apimarvel.adapter.HeroisAdapter
import com.mbappsoftware.apimarvel.adapter.PaginacaoAdapter
import com.mbappsoftware.apimarvel.model.HeroisModel
import com.mbappsoftware.apimarvel.viewmodel.HomeViewModel
import java.io.Serializable

class HomeActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var mViewModel: HomeViewModel
    private lateinit var searchView: SearchView
    private lateinit var recyclerHerois: RecyclerView
    private lateinit var btEsquerda: Button
    private lateinit var btDireita: Button
    private lateinit var btPrimeiro: Button
    private lateinit var btSegundo: Button
    private lateinit var btTerceiro: Button
    private var offSet: Int = 0
    private var mListaTotal: Int = 0
    var mBuscaPersonagem: String = ""

    private var mHeroisList: MutableList<HeroisModel> = arrayListOf()
    private val mHeroisAdapter = HeroisAdapter(mHeroisList, this)

    private var mHeroisListDois: MutableList<HeroisModel> = arrayListOf()
    private val mHeroisDoisAdapter = HeroisAdapter(mHeroisListDois, this)

    private var mHeroisBuscaList: MutableList<HeroisModel> = arrayListOf()
    private val mHeroisBuscaAdapter = HeroisAdapter(mHeroisBuscaList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        iniciaComponentes()
    }
    
    private fun iniciaComponentes() {
        btEsquerda = findViewById<Button>(R.id.btn_left_arrow)
        btDireita = findViewById<Button>(R.id.btn_right_arrow)
        btPrimeiro = findViewById<Button>(R.id.btn_first)
        btSegundo = findViewById<Button>(R.id.btn_second)
        btTerceiro = findViewById<Button>(R.id.btn_third)
        btDireita = findViewById<Button>(R.id.btn_right_arrow)
        searchView = findViewById<SearchView>(R.id.searchView)
        recyclerHerois = findViewById<RecyclerView>(R.id.rv_characters)
        recyclerHerois.layoutManager = LinearLayoutManager(this)

        btPrimeiro.setOnClickListener(this)
        btSegundo.setOnClickListener(this)
        btTerceiro.setOnClickListener(this)
        btEsquerda.setOnClickListener(this)
        btDireita.setOnClickListener(this)

        clickAdapter()
        callSearchView()

        setButton()
        loadFormas()
        observe()

    }

    private fun setButton() {
        btPrimeiro.setText("1")
        btSegundo.setText("2")
        btTerceiro.setText("3")

        changeOneButtonColor(btPrimeiro, btSegundo, btTerceiro)
    }

    private fun callSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(newText: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mBuscaPersonagem = newText.uppercase()

                if(mBuscaPersonagem != null && !mBuscaPersonagem.isEmpty()) {
                    Log.i("fgdf", "mBuscaPersonagem -> $mBuscaPersonagem")
                    mViewModel.buscaPersonagemNome(newText)
                }else{
                    Log.i("fgdf", "mBuscaPersonagem ELSE -> $mBuscaPersonagem")
                    recyclerHerois.adapter = mHeroisDoisAdapter
                    mHeroisDoisAdapter.notifyDataSetChanged()
                }

                return true
            }
        })
    }

    private fun clickAdapter() {
        mHeroisAdapter.onItemClick = {
            val intent = Intent(this, HeroesDetailsActivity::class.java)
            intent.putExtra("herois", it)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        val id = v?.id

        when (id) {
            R.id.btn_left_arrow -> {
                setaEsquerda()
            }
            R.id.btn_right_arrow -> {
                setaDireita()
            }
            R.id.btn_first -> {
                botaoPrimeiro()
            }
            R.id.btn_second -> {
                botaoSegundo()
            }
            R.id.btn_third -> {
                botaoTerceiro()
            }
        }
    }

    private fun setaEsquerda() {

        val valueBtFirst = getValueBtFirst()

        val intBtPrimeiro: Int = (valueBtFirst - 3)
        val stringBtSegundo: Int = (valueBtFirst - 2)
        val stringBtTerceiro: Int = (valueBtFirst - 1)

        val strPrimeiro: String = intBtPrimeiro.toString()
        val strSegundo: String = stringBtSegundo.toString()
        val strTerceiro: String = stringBtTerceiro.toString()

        if (valueBtFirst > 1) {
            changeAllButtonColor(btPrimeiro, btSegundo, btTerceiro)

            btPrimeiro.text = strPrimeiro
            btSegundo.text = strSegundo
            btTerceiro.text = strTerceiro
        }
    }

    private fun setaDireita() {
        val valueBtThird = getValueBtThird()
        var mResulDiv = 0

        if (mListaTotal > 0){
            changeAllButtonColor(btPrimeiro, btSegundo, btTerceiro)

            mResulDiv = mListaTotal/4
            val intBtPrimeiro: Int = (valueBtThird + 1)
            val stringBtSegundo: Int = (valueBtThird + 2)
            val stringBtTerceiro: Int = (valueBtThird + 3)

            val strPrimeiro: String = intBtPrimeiro.toString()
            val strSegundo: String = stringBtSegundo.toString()
            val strTerceiro: String = stringBtTerceiro.toString()

            if (valueBtThird <= mResulDiv) {
            btPrimeiro.text = strPrimeiro
            btSegundo.text = strSegundo
            btTerceiro.text = strTerceiro
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun botaoPrimeiro() {
        changeOneButtonColor(btPrimeiro, btSegundo, btTerceiro)

        val texto: String
        val intText: Int
        texto = btPrimeiro.text.toString()
        intText = texto.toInt()

        if(intText == 1){
            mViewModel.load(offSet)
        }else{
            mViewModel.load((intText - 1) * 4)
        }
    }

    private fun botaoSegundo() {
        changeOneButtonColor(btSegundo, btPrimeiro, btTerceiro)

        val texto: String
        val intText: Int
        texto = btSegundo.text.toString()
        intText = texto.toInt()
        mViewModel.load((intText - 1) * 4)
    }

    private fun botaoTerceiro() {
        changeOneButtonColor(btTerceiro, btPrimeiro, btSegundo)

        val valueBtThird = getValueBtThird()

        mViewModel.load((valueBtThird - 1) * 4)
    }

    private fun changeAllButtonColor(whiteButton1: Button, whiteButton2: Button, whiteButton3: Button) {

        changeColor(whiteButton1, R.drawable.round_button_shape, Color.parseColor("#d42026"))
        changeColor(whiteButton2, R.drawable.round_button_shape, Color.parseColor("#d42026"))
        changeColor(whiteButton3, R.drawable.round_button_shape, Color.parseColor("#d42026"))

    }

    private fun changeOneButtonColor(redButton: Button, whiteButton1: Button, whiteButton2: Button) {

        changeColor(redButton, R.drawable.round_button_shape_red, Color.parseColor("#ffffff"))
        changeColor(whiteButton1, R.drawable.round_button_shape, Color.parseColor("#d42026"))
        changeColor(whiteButton2, R.drawable.round_button_shape, Color.parseColor("#d42026"))

    }

    private fun changeColor(button: Button, shape: Int, color: Int) {
        button.setBackgroundResource(shape)
        button.setTextColor(color)
    }

    private fun getValueBtThird(): Int{
        val texto = btTerceiro.text.toString()
        val resulBtThird = texto.toInt()

        return resulBtThird
    }

    private fun getValueBtFirst(): Int{
        val texto = btPrimeiro.text.toString()
        val resulBtFirst = texto.toInt()

        return resulBtFirst
    }

    private fun loadFormas() {
        mViewModel.load(offSet)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observe() {
        mViewModel.marvelLoad.observe(this, Observer {

            mHeroisList.clear()
            mHeroisBuscaList.clear()
            mHeroisListDois.clear()

            mListaTotal = it.itemModel.total
            Log.i("teste", "TAMANHO LISTA -> " + it.itemModel.results.size)
            for (i in it.itemModel.results.indices) {
                mHeroisList.add(it.itemModel.results.get(i))
                mHeroisListDois.add(it.itemModel.results.get(i))
                Log.i("teste", "\nrecupera item -> " + mHeroisList[i].name)
            }
            recyclerHerois.adapter = mHeroisAdapter
            mHeroisAdapter.notifyDataSetChanged()
        })

        mViewModel.marvelBusca.observe(this, Observer {

            mHeroisBuscaList.clear()
            mHeroisList.clear()

            if (it != null){
                Log.i("fefsfs", "TAMANHO LISTA -> " + it.itemModel.results.size)


                if(it.itemModel.results.isNotEmpty() && it.itemModel.results.size > 0){
                    for (i in it.itemModel.results.indices) {
                        mHeroisBuscaList.add(it.itemModel.results.get(i))
                    }
                    if (!mBuscaPersonagem.isEmpty() && mBuscaPersonagem != "") {
                        recyclerHerois.adapter = mHeroisBuscaAdapter
                        mHeroisBuscaAdapter.notifyDataSetChanged()
                    }
                }

            }
        })
    }


}