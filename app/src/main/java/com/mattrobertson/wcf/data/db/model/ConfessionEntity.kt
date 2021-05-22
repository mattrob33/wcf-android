package com.mattrobertson.wcf.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "confession")
data class ConfessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val chapters: List<ChapterEntity>
)