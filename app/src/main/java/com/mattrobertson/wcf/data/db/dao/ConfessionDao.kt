package com.mattrobertson.wcf.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mattrobertson.wcf.data.db.model.ChapterAndSections
import com.mattrobertson.wcf.data.db.model.ChapterEntity
import com.mattrobertson.wcf.data.db.model.SectionEntity

@Dao
interface ConfessionDao {

    @Query("SELECT * FROM chapters ORDER BY chapter_num ASC")
    suspend fun getAllChapters(): List<ChapterAndSections>

    @Query("SELECT title FROM chapters ORDER BY chapter_num ASC")
    suspend fun getChapterTitles(): List<String>

    @Query("SELECT * FROM chapters WHERE chapter_num=:chapterNum")
    suspend fun getChapter(chapterNum: Int): ChapterAndSections

    @Insert
    suspend fun insertChapters(chapters: List<ChapterEntity>)

    @Insert
    suspend fun insertSections(sections: List<SectionEntity>)

}