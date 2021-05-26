package com.mattrobertson.wcf.repo

import com.mattrobertson.wcf.data.db.dao.ConfessionDao
import com.mattrobertson.wcf.data.db.mappers.mapChapter
import com.mattrobertson.wcf.model.Chapter
import com.mattrobertson.wcf.model.Confession

class ConfessionRepo(
    private val confessionDao: ConfessionDao
) {

    suspend fun getChapter(index: Int): Chapter {
        if (index < 1) throw IllegalArgumentException("Chapter index cannot be less than 1")
        if (index > Confession.NUM_CHAPTERS) throw IllegalArgumentException("Chapter index cannot be greater than ${Confession.NUM_CHAPTERS}")

        return mapChapter(
            confessionDao.getChapter(index)
        )
    }

    suspend fun getAllChapters(): List<Chapter> {
        return confessionDao.getAllChapters().map {
            mapChapter(it)
        }
    }

    suspend fun getChapterTitles() = confessionDao.getChapterTitles()

}