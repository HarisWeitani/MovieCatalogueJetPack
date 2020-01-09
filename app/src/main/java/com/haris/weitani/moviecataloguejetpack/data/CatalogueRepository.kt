package com.haris.weitani.moviecataloguejetpack.data

import androidx.lifecycle.LiveData
import com.haris.weitani.moviecataloguejetpack.common.AppExecutors
import com.haris.weitani.moviecataloguejetpack.data.local.LocalRepository
import com.haris.weitani.moviecataloguejetpack.data.remote.ApiResponse
import com.haris.weitani.moviecataloguejetpack.data.remote.RemoteRepository
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import com.haris.weitani.moviecataloguejetpack.vo.Resource
import java.util.*
import kotlin.collections.ArrayList

class CatalogueRepository(mLocalRepository: LocalRepository, mRemoteRepository: RemoteRepository, mAppExecutors: AppExecutors)
    : CatalogueDataSource
{

    private val localRepository = mLocalRepository
    private val remoteRepository = mRemoteRepository
    private val appExecutors = mAppExecutors

    companion object{
        @Volatile
        lateinit var INSTANCE : CatalogueRepository

        fun getInstance(localRepository: LocalRepository, remoteRepository: RemoteRepository, appExecutors : AppExecutors) : CatalogueRepository{
            if(INSTANCE == null){
                synchronized(CatalogueRepository::class.java){
                    if(INSTANCE == null){
                        INSTANCE = CatalogueRepository(localRepository,remoteRepository,appExecutors)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getPopularMovies(): LiveData<Resource<List<ResultGetMovie>?>?>? {
        return object : NetworkBoundResource<List<ResultGetMovie>, List<ResultGetMovie>>(appExecutors){
            override fun loadFromDB(): LiveData<List<ResultGetMovie>?>? {
                return localRepository.getPopularMovies()
            }

            override fun shouldFetch(data: List<ResultGetMovie>?): Boolean? {
                return data.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultGetMovie>?>?>? {
                return remoteRepository.getPopularMovies()
            }

            override fun saveCallResult(datas: List<ResultGetMovie>?) {
                val movieList = ArrayList<ResultGetMovie>()
                if (datas != null) {
                    for(data in datas){
                        movieList.add(data)
                    }
                    localRepository.insertMovies(movieList)
                }
            }

        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<List<ResultTvShow>?>?>? {
        return object : NetworkBoundResource<List<ResultTvShow>,List<ResultTvShow>>(appExecutors){
            override fun loadFromDB(): LiveData<List<ResultTvShow>?>? {
                return localRepository.getPopularTvShows()
            }

            override fun shouldFetch(data: List<ResultTvShow>?): Boolean? {
                return data.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultTvShow>?>?>? {
                return remoteRepository.getPopularTvShows()
            }

            override fun saveCallResult(datas: List<ResultTvShow>?) {
                val tvShowList = ArrayList<ResultTvShow>()
                if(datas != null){
                    for(data in datas){
                        tvShowList.add(data)
                    }
                    localRepository.insertTvShows(tvShowList)
                }
            }
        }.asLiveData()
    }

}