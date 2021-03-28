package com.example.digisapplication.Network

import com.example.siaappquiz.app.models.NytMostPopulerResp
import com.example.siaappquiz.UTILS.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApi {

    @GET("svc/mostpopular/v2/viewed/{period}.json?api-key=${Constants.keyName}")
   suspend fun getDataMostPopular(
        @Path("period") period: Int
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