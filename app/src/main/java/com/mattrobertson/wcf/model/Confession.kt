package com.mattrobertson.wcf.model

data class Confession(
    val chapters: List<Chapter>
) {
    companion object {
        const val NUM_CHAPTERS = 33
    }
}