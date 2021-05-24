package com.mattrobertson.wcf

import androidx.lifecycle.ViewModel
import com.mattrobertson.wcf.repo.ConfessionRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReadingViewModel @Inject constructor(
    val repo: ConfessionRepo
): ViewModel() {

    val chapterTitles: List<String> by lazy {
        repo.confession.chapters.map { it.title }
    }

}