package com.mattrobertson.wcf.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapters")
data class ChapterEntity(
    @PrimaryKey
    @ColumnInfo(name = "chapter_num")
    val chapterNum: Int,

    val title: String
)