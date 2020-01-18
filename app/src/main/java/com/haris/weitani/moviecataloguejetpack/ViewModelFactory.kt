package com.haris.weitani.moviecataloguejetpack

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository
import com.haris.weitani.moviecataloguejetpack.detailview.DetailViewModel
import com.haris.weitani.moviecataloguejetpack.di.Injection
import com.haris.weitani.moviecataloguejetpack.favoriteactivity.FavoriteViewModel
import com.haris.weitani.moviecataloguejetpack.mainactivity.MainViewModel

class ViewModelFactory (catalogueRepository: CatalogueRepository) :
    NewInstanceFactory() {

    private val mCatalogueRepository = catalogueRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mCatalogueRepository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(mCatalogueRepository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(mCatalogueRepository) as T
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