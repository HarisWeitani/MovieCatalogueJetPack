package com.haris.weitani.moviecataloguejetpack.data.local

import android.database.Cursor
import androidx.paging.DataSource
import androidx.room.*
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie

@Dao
interface MovieDAO {

    @Query("SELECT * FROM ResultGetMovie ORDER BY popularity DESC")
    fun getAllMovie() : List<ResultGetMovie>

    @Query("SELECT * FROM ResultGetMovie WHERE is_favorite = 1")
    fun getAllFavMovie() : DataSource.Factory<Int,ResultGetMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(vararg movie : ResultGetMovie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(vararg movie : ResultGetMovie)

    @Delete
    fun deleteMovie(vararg movie: ResultGetMovie)

    @Query("SELECT * FROM ResultGetMovie WHERE is_favorite = 1")
    fun selectAllFavMovie() : Cursor

    @Query("SELECT * FROM ResultGetMovie WHERE id = :id")
    fun selectById(id: Long) : ResultGetMovie
}