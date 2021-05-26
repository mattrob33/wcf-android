package com.mattrobertson.wcf.data.db.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mattrobertson.wcf.data.db.ConfessionDatabase
import com.mattrobertson.wcf.data.db.model.ChapterEntity
import com.mattrobertson.wcf.data.db.model.SectionEntity
import com.mattrobertson.wcf.data.json.ConfessionParser
import kotlinx.coroutines.coroutineScope

class PopulateDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val jsonConfession = ConfessionParser().jsonConfession

            val chapters = mutableListOf<ChapterEntity>()
            val sections = mutableListOf<SectionEntity>()

            jsonConfession.chapters.forEachIndexed { chapterIndex, chapter ->
                val chapterNum = chapterIndex + 1

                chapters.add(
                    ChapterEntity(chapterNum = chapterNum, title = chapter.title)
                )

                chapter.sections.forEachIndexed { sectionIndex, section ->
                    val sectionNum = sectionIndex + 1
                    sections.add(
                        SectionEntity(
                            id = "${chapterNum}_${sectionNum}",
                            chapterNum = chapterNum,
                            text = section.text
                        )
                    )
                }
            }

            val database = ConfessionDatabase.getInstance(applicationContext)

            database.confessionDao().insertChapters(chapters)
            database.confessionDao().insertSections(sections)

            Result.success()

        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "PopulateDatabaseWorker"
    }
}