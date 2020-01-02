package com.haris.weitani.moviecataloguejetpack.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.haris.weitani.moviecataloguejetpack.BuildConfig
import com.haris.weitani.moviecataloguejetpack.common.API
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository{

    companion object{
        lateinit var INSTANCE : RemoteRepository
        fun getInstance() : RemoteRepository{
            if(INSTANCE == null){
                INSTANCE = RemoteRepository()
            }
            return INSTANCE
        }

        private fun networkLogging(string: String){
            Log.i(GlobalVal.NETWORK_LOG, string)
        }
    }

    fun getPopularMovies() : LiveData<ArrayList<ResultGetMovie>>{
        val movies =
            API.networkApi().getPopularMovies(BuildConfig.API_KEY)
                .enqueue(object : Callback<ResponseGetMovie>{
                    override fun onFailure(call: Call<ResponseGetMovie>, t: Throwable) {
                        networkLogging(t.localizedMessage)
                    }

                    override fun onResponse(
                        call: Call<ResponseGetMovie>,
                        response: Response<ResponseGetMovie>
                    ) {
                        networkLogging(response.message())

                    }

                })
    }

}