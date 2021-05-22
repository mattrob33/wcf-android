package com.mattrobertson.wcf.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mattrobertson.wcf.data.db.model.ChapterEntity

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapters")
    suspend fun getAllChapters(): List<ChapterEntity>

    @Query("SELECT * FROM chapters WHERE chapterNum=:chapterNum")
    suspend fun getChapter(chapterNum: Int): ChapterEntity

    @Insert
    suspend fun insertAll(vararg chapters: ChapterEntity)

    @Delete
    suspend fun delete(chapter: ChapterEntity)

}