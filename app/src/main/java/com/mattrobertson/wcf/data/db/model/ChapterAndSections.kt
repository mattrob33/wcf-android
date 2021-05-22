package com.mattrobertson.wcf.data.db.model

import androidx.room.Relation

data class ChapterAndSections(
    val chapterNum: Int,
    val title: String,

    @Relation(parentColumn = "chapterNum", entityColumn = "id")
    val sections: List<SectionEntity>
)