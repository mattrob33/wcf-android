package com.mattrobertson.wcf.data.db.mappers

import com.mattrobertson.wcf.data.db.model.ChapterEntity
import com.mattrobertson.wcf.data.db.model.SectionEntity
import com.mattrobertson.wcf.data.json.model.ChapterJson
import com.mattrobertson.wcf.data.json.model.SectionJson

fun mapChapter(json: ChapterJson, chapterNum: Int): ChapterEntity {
    return ChapterEntity(
        chapterNum = chapterNum,
        title = json.title,
        sections = json.sectionJsons.map { mapSection(it) }
    )
}

fun mapSection(json: SectionJson): SectionEntity {
    return SectionEntity(text = json.text)
}