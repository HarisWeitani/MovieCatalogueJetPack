package com.haris.weitani.moviecataloguejetpack.di

import android.app.Application
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository

object Injection {

    fun provideRepository(application: Application?): CatalogueRepository {
        val database: AcademyDatabase = AcademyDatabase.getInstance(application)
        val localRepository: LocalRepository = LocalRepository.getInstance(database.academyDao())
        val remoteRepository: RemoteRepository =
            RemoteRepository.getInstance(JsonHelper(application))
        val appExecutors = AppExecutors()
        return AcademyRepository.getInstance(localRepository, remoteRepository, appExecutors)
    }
}