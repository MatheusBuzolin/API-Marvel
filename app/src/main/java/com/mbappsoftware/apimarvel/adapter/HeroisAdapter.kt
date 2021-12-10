package com.mbappsoftware.apimarvel.adapter

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.mbappsoftware.apimarvel.R
import com.mbappsoftware.apimarvel.model.HeroisModel
import retrofit2.http.Url
import java.security.AccessController.getContext

class HeroisAdapter(var mHeroisList: List<HeroisModel>, context: Context) : RecyclerView.Adapter<HeroisAdapter.ViewHolder>(){

    var context = context
    var onItemClick: ((HeroisModel)->Unit) ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var hm: HeroisModel = mHeroisList[position]
        holder.nomePersonagem.setText(hm.name)

        var url = "${hm.thumbnail.path}.${hm.thumbnail.extension}"

        Glide.with(context)
            .asBitmap()
            .load(url.replace("http", "https"))
            .circleCrop()
            .error(R.drawable.ironman)
            .placeholder(R.drawable.ironman)
            .into(holder.imagemPersonagem);
    }

    override fun getItemCount(): Int {
        return mHeroisList.count()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomePersonagem: TextView
        val imagemPersonagem: ImageView

        init {
            nomePersonagem = view.findViewById(R.id.tv_character)
            imagemPersonagem = view.findViewById(R.id.iv_character)

            itemView.setOnClickListener {
                onItemClick?.invoke(mHeroisList[adapterPosition])
            }
        }
    }
}