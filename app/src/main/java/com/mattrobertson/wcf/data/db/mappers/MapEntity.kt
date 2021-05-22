package com.mattrobertson.wcf.data.db.mappers

import com.mattrobertson.wcf.data.db.model.ChapterEntity
import com.mattrobertson.wcf.data.db.model.ConfessionEntity
import com.mattrobertson.wcf.data.db.model.SectionEntity
import com.mattrobertson.wcf.model.Chapter
import com.mattrobertson.wcf.model.Confession
import com.mattrobertson.wcf.model.Section

fun mapConfession(entity: ConfessionEntity): Confession {
    return Confession(
        chapters = entity.chapters.map { mapChapter(it) }
    )
}

fun mapChapter(entity: ChapterEntity): Chapter {
    return Chapter(
        title = entity.title,
        sections = entity.sections.map { mapSection(it) }
    )
}

fun mapSection(entity: SectionEntity): Section {
    return Section(text = entity.text)
}