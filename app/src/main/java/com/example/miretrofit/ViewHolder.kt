package com.example.miretrofit

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View):RecyclerView.ViewHolder(view) {

    var userId = view.findViewById<TextView>(R.id.textViewUserId)
    var id =view.findViewById<TextView>(R.id.textViewId)
    var title = view.findViewById<TextView>(R.id.textViewTitle)
    var body = view.findViewById<TextView>(R.id.textViewBody)

    fun bind(post:Post){
        userId.text=post.userId.toString()
        id.text=post.id.toString()
        title.text=post.title.toString()
        body.text=post.body.toString()
    }



}