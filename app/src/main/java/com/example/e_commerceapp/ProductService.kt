package com.example.e_commerceapp

import retrofit2.Response
import retrofit2.http.GET


import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface ProductApiServices {
    @GET("productdetails/6701/253620?lang=en&store=KWD")
    fun getProducts(): Observable<ProductModel>

    companion object Factory{
        fun create():ProductApiServices{
            val retrofit= Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://klinq.com/rest/V1/")
                .build()

            return (retrofit.create(ProductApiServices::class.java))
        }
    }
}
