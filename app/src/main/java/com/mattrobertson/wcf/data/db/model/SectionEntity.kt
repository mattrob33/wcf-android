package com.mattrobertson.wcf.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sections",
    foreignKeys = [
        ForeignKey(
            entity = ChapterEntity::class,
            parentColumns = ["chapter_num"],
            childColumns = ["chapter_num"]
        )
    ]
)
data class SectionEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "chapter_num") val chapterNum: Int,
    val text: String
)