package com.haris.weitani.moviecataloguejetpack.common

import com.google.gson.GsonBuilder
import com.haris.weitani.moviecataloguejetpack.data.remote.ResponseGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResponseTvShows
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface API {

    @GET("movie/popular")
    fun getPopularMovies(
            @Query("api_key") apiKey : String
        ) : Call<ResponseGetMovie>

    @GET("tv/popular")
    fun getPopularTvShow(
            @Query("api_key") apiKey : String
        ) : Call<ResponseTvShows>

    companion object Factory{

//        https://image.tmdb.org/t/p/w185/h6Wi81XNXCjTAcdstiCLRykN3Pa.jpg

        private var serverUrl = "https://api.themoviedb.org/3/"

        fun networkApi() : API {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(60,TimeUnit.SECONDS)
                .build()
            //try catch ini
            val retrofit = Retrofit.Builder()
                .baseUrl(serverUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

            return retrofit.create(API::class.java)
        }
    }

}