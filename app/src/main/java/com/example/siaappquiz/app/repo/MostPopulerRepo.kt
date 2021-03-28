package com.example.siaappquiz.app.repo

import androidx.lifecycle.MutableLiveData
import com.example.digisapplication.Network.MyApi
import com.example.digisapplication.Network.SafeApiRequest
import com.example.siaappquiz.app.models.NytMostPopulerResp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MostPopulerRepo (
    private val apiService: MyApi
): SafeApiRequest() {

    private val dataResponse= MutableLiveData<NytMostPopulerResp>()

    suspend fun getDataPopular(period :Int): MutableLiveData<NytMostPopulerResp>? {
        return withContext(Dispatchers.IO) {
            return@withContext getDataFromApi(period )
        }
    }

    private suspend fun getDataFromApi(period :Int): MutableLiveData<NytMostPopulerResp>? {

        try {
            val response = apiRequest {
                apiService.getDataMostPopular(period)
            }

            dataResponse.postValue(response)
            return dataResponse
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}