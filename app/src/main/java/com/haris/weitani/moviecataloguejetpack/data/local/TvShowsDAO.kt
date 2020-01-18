package com.haris.weitani.moviecataloguejetpack.data.local

import androidx.paging.DataSource
import androidx.room.*
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow

@Dao
interface TvShowsDAO {

    @Query("SELECT * FROM ResultTvShow ORDER BY popularity DESC")
    fun getAllTvShows() : List<ResultTvShow>

    @Query("SELECT * FROM ResultTvShow WHERE is_favorite = 1")
    fun getAllFavTvShows() : DataSource.Factory<Int,ResultTvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(vararg tvShows: ResultTvShow)

    @Update
    fun updateTvSHows(vararg tvShows: ResultTvShow)

    @Delete
    fun deleteTvShows(vararg tvShows: ResultTvShow)

    @Query("SELECT * FROM ResultTvShow WHERE id = :id")
    fun selectById(id: Long) : ResultTvShow
}