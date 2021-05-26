package com.mattrobertson.wcf.data.db.model

import androidx.room.Embedded
import androidx.room.Relation

data class ChapterAndSections(
    @Embedded
    val chapter: ChapterEntity,

    @Relation(parentColumn = "chapter_num", entityColumn = "chapter_num")
    val sections: List<SectionEntity>
)