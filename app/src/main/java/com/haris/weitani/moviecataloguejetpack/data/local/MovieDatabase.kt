package com.haris.weitani.moviecataloguejetpack.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie

@Database(entities = arrayOf(ResultGetMovie::class), version = 1, exportSchema = false)
public abstract class MovieDatabase : RoomDatabase(){

    abstract fun movieDao() : MovieDAO

    companion object{

        @Volatile
        private var INSTANCE : MovieDatabase? = null

        fun getDatabase(context: Context) : MovieDatabase{
            val tempInstance = INSTANCE
            if( tempInstance!= null) return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movies_db"
                 ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}