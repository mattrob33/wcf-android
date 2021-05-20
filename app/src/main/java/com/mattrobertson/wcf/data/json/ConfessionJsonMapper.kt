package com.mattrobertson.wcf.data.json

import com.mattrobertson.wcf.model.Chapter
import com.mattrobertson.wcf.model.Section
import com.mattrobertson.wcf.model.Confession

fun mapJsonToConfession(json: com.mattrobertson.wcf.data.json.model.Confession): Confession {
    return Confession(
        json.chapters.map {mapJsonToChapter(it) }
    )
}

fun mapJsonToChapter(json: com.mattrobertson.wcf.data.json.model.Chapter): Chapter {
    return Chapter(
        title = json.title,
        sections = json.sections.map { mapJsonToSection(it) }
    )
}

fun mapJsonToSection(json: com.mattrobertson.wcf.data.json.model.Section): Section {
    return Section(json.text)
}