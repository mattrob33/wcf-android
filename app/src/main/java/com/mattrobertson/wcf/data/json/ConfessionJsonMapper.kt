package com.mattrobertson.wcf.data.json

import com.mattrobertson.wcf.model.Chapter
import com.mattrobertson.wcf.model.Section
import com.mattrobertson.wcf.model.Confession

fun mapJsonToConfession(json: com.mattrobertson.wcf.data.json.model.ConfessionJson): Confession {
    return Confession(
        json.chapterJsons.map {mapJsonToChapter(it) }
    )
}

fun mapJsonToChapter(json: com.mattrobertson.wcf.data.json.model.ChapterJson): Chapter {
    return Chapter(
        title = json.title,
        sections = json.sectionJsons.map { mapJsonToSection(it) }
    )
}

fun mapJsonToSection(json: com.mattrobertson.wcf.data.json.model.SectionJson): Section {
    return Section(json.text)
}