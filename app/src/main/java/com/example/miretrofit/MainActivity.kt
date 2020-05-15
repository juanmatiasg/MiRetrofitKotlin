package com.example.miretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.SearchView.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_post.*
import retrofit2.*

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST


class MainActivity : AppCompatActivity(), OnQueryTextListener {

    lateinit var mRecyclerView: RecyclerView
    var adapterPost: AdapterPost = AdapterPost()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView.setOnQueryTextListener(this)
        setupRecyclerView()

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun setupRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }


    fun searchById(query: String) {
        val service = getRetrofit().create(APIService::class.java)
        val post = service.getPostById(query)
        post.enqueue(object : Callback<Post> {
            override fun onFailure(call: retrofit2.Call<Post>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No hay conexion", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: retrofit2.Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val objeto = response.body() as Post
                    val lista: MutableList<Post> = mutableListOf(
                        Post(
                            userId = objeto.userId,
                            id = objeto.id,
                            title = objeto.title,
                            body = objeto.body
                        )
                    )
                    adapterPost.AdapterPost(lista, this@MainActivity)
                    mRecyclerView.adapter = adapterPost

                }
                else{
                    Toast.makeText(this@MainActivity,"No se ha cargado los datos",Toast.LENGTH_SHORT).show()
                }
            }


        })


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        searchById(newText)
        return true
    }



}

