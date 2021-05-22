package com.mattrobertson.wcf.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConfessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val chapters: List<ChapterEntity>
)