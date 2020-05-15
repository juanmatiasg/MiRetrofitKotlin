package com.example.miretrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface APIService {
    @GET("posts")
    fun getAllPost():Call<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id")id:String):Call<Post>


}