package com.mbappsoftware.apimarvel.view

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mbappsoftware.apimarvel.R
import com.mbappsoftware.apimarvel.model.HeroisModel
import javax.sql.DataSource

class HeroesDetailsActivity : AppCompatActivity() {

    private lateinit var ivHerois: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvData: TextView
    private lateinit var tvDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes_details)

        val herois: HeroisModel = intent.getSerializableExtra("herois") as HeroisModel

        iniciaComponentes(herois)
    }

    private fun iniciaComponentes(herois: HeroisModel) {
        ivHerois = findViewById(R.id.iv_character)
        tvName = findViewById(R.id.tv_character)
        tvData = findViewById(R.id.tv_release_date)
        tvDescription = findViewById(R.id.tv_description)

        val name = herois.name
        val description = herois.description
        val path = herois.thumbnail.path
        val extension = herois.thumbnail.extension
        val url = "${path}.${extension}"

        tvName.text = name.toString()
        if (description.isNotEmpty()){
            tvDescription.text = description.toString()
        }else{
            tvDescription.text = "Sem descrição"
        }

        Glide.with(this)
            .load(url.replace("http", "https"))
            .error(R.drawable.ironman)
            .placeholder(R.drawable.ironman)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Log.i("fefef", "ERRO AO CARREGAR: " + e )
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: com.bumptech.glide.load.DataSource?, isFirstResource: Boolean): Boolean {
                    Log.i("fefef", "CARREGOU" )
                    return false
                }
            }).into(ivHerois)
    }
}