package com.haris.weitani.moviecataloguejetpack.di

import android.app.Application
import com.haris.weitani.moviecataloguejetpack.common.AppExecutors
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository
import com.haris.weitani.moviecataloguejetpack.data.local.LocalRepository
import com.haris.weitani.moviecataloguejetpack.data.local.MovieDatabase
import com.haris.weitani.moviecataloguejetpack.data.local.TVShowsDatabase
import com.haris.weitani.moviecataloguejetpack.data.remote.RemoteRepository

object Injection {

    fun provideRepository(application: Application?): CatalogueRepository {
        val movieDb: MovieDatabase = MovieDatabase.getDatabase(application?.applicationContext!!)
        val tvShowDb: TVShowsDatabase = TVShowsDatabase.getDatabase(application.applicationContext!!)
        val localRepository: LocalRepository = LocalRepository.getInstance(movieDb.movieDao(),tvShowDb.tvShowsDao())
        val remoteRepository: RemoteRepository = RemoteRepository.getInstance()
        val appExecutors = AppExecutors()

        return CatalogueRepository.getInstance(localRepository, remoteRepository, appExecutors)
    }
}