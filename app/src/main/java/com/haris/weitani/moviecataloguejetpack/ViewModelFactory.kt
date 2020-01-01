package com.haris.weitani.moviecataloguejetpack

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository
import com.haris.weitani.moviecataloguejetpack.detail_view.DetailViewModel
import com.haris.weitani.moviecataloguejetpack.di.Injection
import com.haris.weitani.moviecataloguejetpack.main_activity.MainViewModel

class ViewModelFactory private constructor(catalogueRepository: CatalogueRepository) :
    NewInstanceFactory() {

    init {
        mCatalogueRepository = catalogueRepository
    }

    private val mCatalogueRepository: CatalogueRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mCatalogueRepository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(mCatalogueRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application?): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            ViewModelFactory(Injection.provideRepository(application))
                    }
                }
            }
            return INSTANCE
        }
    }

}