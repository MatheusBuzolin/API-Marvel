package com.mbappsoftware.apimarvel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbappsoftware.apimarvel.R
import com.mbappsoftware.apimarvel.model.HeroisModel

class PaginacaoAdapter(var mPaginaList: List<Int>) : RecyclerView.Adapter<PaginacaoAdapter.ViewHolder>() {

    //private var mHeroisList: List<HeroisModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var hm: Int = mPaginaList[position]
    }

    override fun getItemCount(): Int {
        return mPaginaList.count()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            //textView = view.findViewById(R.id.textView)
        }
    }
}