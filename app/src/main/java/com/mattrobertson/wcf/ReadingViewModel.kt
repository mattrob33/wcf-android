package com.mattrobertson.wcf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mattrobertson.wcf.model.Confession
import com.mattrobertson.wcf.repo.ConfessionRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ReadingViewModel @Inject constructor(
    private val repo: ConfessionRepo
): ViewModel() {

    val confession: Confession by lazy {
        runBlocking {
            Confession(
                chapters = repo.getAllChapters()
            )
        }
    }

    val chapterTitles: List<String> by lazy {
        runBlocking {
            repo.getChapterTitles()
        }
    }

}