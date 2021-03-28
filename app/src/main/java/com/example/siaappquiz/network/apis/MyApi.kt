package com.example.digisapplication.Network

import com.example.siaappquiz.app.models.NytMostPopulerResp
import com.example.siaappquiz.UTILS.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApi {

    @GET("svc/mostpopular/v2/viewed/7.json?api-key=mTmA8p0TEZEkVyyPrQ1zZgF1gQTc5c1l")
   suspend fun getDataMostPopular(
    ): Response<NytMostPopulerResp>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor? = null
        ): MyApi {

            return Retrofit.Builder()
                .baseUrl(Constants.DataMostPopular)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}