package com.mattrobertson.wcf.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapters")
data class ChapterEntity(
    @PrimaryKey val chapterNum: Int = 0,
    val title: String
)