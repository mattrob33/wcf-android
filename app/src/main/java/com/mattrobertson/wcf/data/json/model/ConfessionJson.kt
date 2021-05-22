package com.mattrobertson.wcf.data.json.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfessionJson(
    val chapterJsons: List<ChapterJson>
)