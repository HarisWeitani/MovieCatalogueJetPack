package com.haris.weitani.moviecataloguejetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.haris.weitani.moviecataloguejetpack.common.AppExecutors
import com.haris.weitani.moviecataloguejetpack.data.local.LocalRepository
import com.haris.weitani.moviecataloguejetpack.data.remote.ApiResponse
import com.haris.weitani.moviecataloguejetpack.data.remote.RemoteRepository
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import com.haris.weitani.moviecataloguejetpack.vo.Resource
import kotlin.collections.ArrayList

class CatalogueRepository(
    mLocalRepository: LocalRepository,
    mRemoteRepository: RemoteRepository,
    mAppExecutors: AppExecutors
) : CatalogueDataSource {

    private val localRepository = mLocalRepository
    private val remoteRepository = mRemoteRepository
    private val appExecutors = mAppExecutors

    companion object {
        @Volatile
        var INSTANCE: CatalogueRepository? = null

        fun getInstance(
            localRepository: LocalRepository,
            remoteRepository: RemoteRepository,
            appExecutors: AppExecutors
        ): CatalogueRepository {
            if (INSTANCE == null) {
                synchronized(CatalogueRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            CatalogueRepository(localRepository, remoteRepository, appExecutors)
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun getPopularMovies(): LiveData<Resource<List<ResultGetMovie>?>?>? {
        return object :
            NetworkBoundResource<List<ResultGetMovie>, List<ResultGetMovie>>(appExecutors) {
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
                    for (data in datas) {
                        movieList.add(data)
                    }
                    localRepository.insertMovies(movieList)
                }
            }

        }.asLiveData()
    }

    override fun getMoviesById(movieId: Long): LiveData<Resource<ResultGetMovie?>?>? {
        return object : NetworkBoundResource<ResultGetMovie, ResultGetMovie>(appExecutors) {
            override fun loadFromDB(): LiveData<ResultGetMovie?>? {
                return localRepository.getMoviesById(movieId)
            }

            override fun shouldFetch(data: ResultGetMovie?): Boolean? {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<ResultGetMovie?>?>? {
                return remoteRepository.getMovieById(movieId)
            }

            override fun saveCallResult(data: ResultGetMovie?) {
                //do nothing
            }
        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<List<ResultTvShow>?>?>? {
        return object : NetworkBoundResource<List<ResultTvShow>, List<ResultTvShow>>(appExecutors) {
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
                if (datas != null) {
                    for (data in datas) {
                        tvShowList.add(data)
                    }
                    localRepository.insertTvShows(tvShowList)
                }
            }
        }.asLiveData()
    }

    override fun getTvShowsById(tvShowId: Long): LiveData<Resource<ResultTvShow?>?>? {
        return object : NetworkBoundResource<ResultTvShow,ResultTvShow>(appExecutors){
            override fun loadFromDB(): LiveData<ResultTvShow?>? {
                return localRepository.getTvShowById(tvShowId)
            }

            override fun shouldFetch(data: ResultTvShow?): Boolean? {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<ResultTvShow?>?>? {
                return remoteRepository.getTvShowById(tvShowId)
            }

            override fun saveCallResult(data: ResultTvShow?) {
                //do nothing
            }

        }.asLiveData()
    }

    override fun getAllFavMovies(): LiveData<PagedList<ResultGetMovie>> {
        return LivePagedListBuilder(localRepository.getFavMoviesPaged(),10).build()
    }

    override fun addFavMovie(data: ResultGetMovie) {
        localRepository.addFavMovie(data)
    }

    override fun removeFavMovie(data: ResultGetMovie) {
        localRepository.removeFavMovie(data)
    }

    override fun getAllFavTvShows(): LiveData<PagedList<ResultTvShow>> {
        return LivePagedListBuilder(localRepository.getFavTvShowsPaged(),10).build()
    }

    override fun addFavTvShow(data: ResultTvShow) {
        localRepository.addFavTvShow(data)
    }

    override fun removeFavTvShow(data: ResultTvShow) {
        localRepository.removeFavTvShow(data)
    }

}