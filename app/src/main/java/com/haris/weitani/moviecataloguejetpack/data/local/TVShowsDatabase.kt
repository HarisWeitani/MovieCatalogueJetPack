package com.haris.weitani.moviecataloguejetpack.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow

@Database(entities = arrayOf(ResultTvShow::class), version = 1, exportSchema = false)
public abstract class TVShowsDatabase : RoomDatabase(){

    abstract fun tvShowsDao() : TvShowsDAO

    companion object{

        @Volatile
        private var INSTANCE : TVShowsDatabase? = null

        fun getDatabase(context: Context) : TVShowsDatabase{
            val tempInstance = INSTANCE
            if( tempInstance!= null) return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TVShowsDatabase::class.java,
                    "tvshows_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}