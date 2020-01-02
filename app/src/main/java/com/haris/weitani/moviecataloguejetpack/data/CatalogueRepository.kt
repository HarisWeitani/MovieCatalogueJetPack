package com.haris.weitani.moviecataloguejetpack.data

import androidx.lifecycle.LiveData
import com.haris.weitani.moviecataloguejetpack.data.local.LocalRepository
import com.haris.weitani.moviecataloguejetpack.data.remote.RemoteRepository

class CatalogueRepository(mLocalRepository: LocalRepository, mRemoteRepository: RemoteRepository)
    : CatalogueDataSource
{

    private val localRepository = mLocalRepository
    private val remoteRepository = mRemoteRepository

    companion object{
        @Volatile
        lateinit var INSTANCE : CatalogueRepository

        fun getInstance(localRepository: LocalRepository, remoteRepository: RemoteRepository) : CatalogueRepository{
            if(INSTANCE == null){
                synchronized(CatalogueRepository::class.java){
                    if(INSTANCE == null){
                        INSTANCE = CatalogueRepository(localRepository,remoteRepository)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getPopularMovies(): LiveData<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPopularTvShows(): LiveData<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}