package com.mattrobertson.wcf.data.json.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChapterJson(
    val title: String,
    val sections: List<SectionJson>
)