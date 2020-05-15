package com.example.miretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterPost:RecyclerView.Adapter<ViewHolder>(){
    var listaPost:MutableList<Post> = mutableListOf()
    lateinit var context: Context

    fun AdapterPost(listaPost:MutableList<Post>,context: Context){
        this.listaPost=listaPost
        this.context=context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_post,parent,false))
    }

    override fun getItemCount(): Int {
        return this.listaPost.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = this.listaPost.get(position)
        holder.bind(item)
    }
}