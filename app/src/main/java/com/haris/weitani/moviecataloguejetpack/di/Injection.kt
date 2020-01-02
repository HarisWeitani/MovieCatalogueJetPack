package com.haris.weitani.moviecataloguejetpack.di

import android.app.Application
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository
import com.haris.weitani.moviecataloguejetpack.data.local.LocalRepository
import com.haris.weitani.moviecataloguejetpack.data.remote.RemoteRepository

object Injection {

    fun provideRepository(application: Application?): CatalogueRepository {
        val database: AcademyDatabase = AcademyDatabase.getInstance(application)
        val localRepository: LocalRepository = LocalRepository.getInstance(database.academyDao())
        val remoteRepository: RemoteRepository = RemoteRepository.getInstance()

        return CatalogueRepository.getInstance(localRepository, remoteRepository)
    }
}