package com.haris.weitani.moviecataloguejetpack.data.local

import android.database.Cursor
import androidx.room.*
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie

@Dao
interface MovieDAO {

    @Query("SELECT * FROM ResultGetMovie ORDER BY popularity DESC")
    fun getAllMovie() : List<ResultGetMovie>

    @Query("SELECT * FROM ResultGetMovie WHERE is_favorite = 1")
    fun getAllFavMovie() : List<ResultGetMovie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(vararg movie : ResultGetMovie)

    @Update
    fun updateMovie(vararg movie : ResultGetMovie)

    @Delete
    fun deleteMovie(vararg movie: ResultGetMovie)

    @Query("SELECT * FROM ResultGetMovie WHERE is_favorite = 1")
    fun selectAllFavMovie() : Cursor

    @Query("SELECT * FROM ResultGetMovie WHERE id = :id")
    fun selectById(id: Long) : ResultGetMovie
}