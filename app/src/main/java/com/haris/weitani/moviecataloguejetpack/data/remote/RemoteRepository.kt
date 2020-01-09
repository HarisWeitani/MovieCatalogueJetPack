package com.haris.weitani.moviecataloguejetpack.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haris.weitani.moviecataloguejetpack.BuildConfig
import com.haris.weitani.moviecataloguejetpack.common.API
import com.haris.weitani.moviecataloguejetpack.common.EspressoIdlingResource
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    companion object {
        lateinit var INSTANCE: RemoteRepository
        fun getInstance(): RemoteRepository {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository()
            }
            return INSTANCE
        }

        private fun networkLogging(string: String) {
            Log.i(GlobalVal.NETWORK_LOG, string)
        }
    }

    fun getPopularMovies(): LiveData<ApiResponse<List<ResultGetMovie>?>?>? {
        EspressoIdlingResource.increment()

        val movies = MutableLiveData<ApiResponse<List<ResultGetMovie>?>?>()

        API.networkApi().getPopularMovies(BuildConfig.API_KEY)
            .enqueue(object : Callback<ResponseGetMovie> {
                override fun onFailure(call: Call<ResponseGetMovie>, t: Throwable) {
                    networkLogging(t.localizedMessage)
                    EspressoIdlingResource.decrement()
                    ApiResponse.error("error",null)
                }

                override fun onResponse(
                    call: Call<ResponseGetMovie>,
                    response: Response<ResponseGetMovie>
                ) {
                    networkLogging(response.message())
                    EspressoIdlingResource.decrement()
                    if(response.isSuccessful){
                        movies.postValue(ApiResponse.success(response.body()?.results))
                    }else{
                        ApiResponse.empty("empty",null)
                    }
                }

            })
        return movies
    }

    fun getPopularTvShows() : LiveData<ApiResponse<List<ResultTvShow>?>?>{
        EspressoIdlingResource.increment()

        val tvShows = MutableLiveData<ApiResponse<List<ResultTvShow>?>?>()

        API.networkApi().getPopularTvShow(BuildConfig.API_KEY)
            .enqueue(object : Callback<ResponseTvShows> {
                override fun onFailure(call: Call<ResponseTvShows>, t: Throwable) {
                    networkLogging(t.localizedMessage)
                    EspressoIdlingResource.decrement()
                    ApiResponse.error("error",null)
                }

                override fun onResponse(
                    call: Call<ResponseTvShows>,
                    response: Response<ResponseTvShows>
                ) {
                    networkLogging(response.message())
                    EspressoIdlingResource.decrement()
                    if(response.isSuccessful){
                        tvShows.postValue(ApiResponse.success(response.body()?.results))
                    }else{
                        ApiResponse.empty("empty",null)
                    }
                }
            })
        return tvShows
    }

}