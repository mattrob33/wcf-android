package com.mattrobertson.wcf.repo

import com.mattrobertson.wcf.data.json.ConfessionParser
import com.mattrobertson.wcf.model.Chapter
import com.mattrobertson.wcf.model.Confession
import java.lang.IllegalArgumentException

class ConfessionRepo {

    private val confession = ConfessionParser().confession

    fun getChapter(index: Int): Chapter {
        if (index < 0) throw IllegalArgumentException("Chapter index cannot be negative")
        if (index >= Confession.NUM_CHAPTERS) throw IllegalArgumentException("Chapter index cannot be greater than ${Confession.NUM_CHAPTERS - 1}")

        return confession.chapters[index]
    }

    fun getAllChapters() = confession.chapters

}