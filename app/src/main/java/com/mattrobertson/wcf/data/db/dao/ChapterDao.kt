package com.mattrobertson.wcf.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mattrobertson.wcf.data.db.model.ChapterEntity

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapters")
    fun getAllChapters(): List<ChapterEntity>

    @Query("SELECT * FROM chapters WHERE chapterNum=:chapterNum")
    fun getChapter(chapterNum: Int): ChapterEntity

    @Insert
    fun insertAll(vararg chapters: ChapterEntity)

    @Delete
    fun delete(chapter: ChapterEntity)

}