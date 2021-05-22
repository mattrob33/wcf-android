package com.mattrobertson.wcf.data.db.mappers

import com.mattrobertson.wcf.data.db.model.ChapterEntity
import com.mattrobertson.wcf.data.db.model.ConfessionEntity
import com.mattrobertson.wcf.data.db.model.SectionEntity
import com.mattrobertson.wcf.data.json.model.ChapterJson
import com.mattrobertson.wcf.data.json.model.ConfessionJson
import com.mattrobertson.wcf.data.json.model.SectionJson

fun mapConfession(json: ConfessionJson): ConfessionEntity {
    return ConfessionEntity(
        chapters = json.chapterJsons.map { mapChapter(it) }
    )
}

fun mapChapter(json: ChapterJson): ChapterEntity {
    return ChapterEntity(
        title = json.title,
        sections = json.sectionJsons.map { mapSection(it) }
    )
}

fun mapSection(json: SectionJson): SectionEntity {
    return SectionEntity(text = json.text)
}