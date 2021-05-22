package com.mattrobertson.wcf.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.mattrobertson.wcf.data.db.dao.ChapterDao
import com.mattrobertson.wcf.data.db.model.ChapterEntity
import com.mattrobertson.wcf.data.db.model.SectionEntity
import com.mattrobertson.wcf.data.db.worker.PopulateDatabaseWorker

@Database(
    entities = [
        ChapterEntity::class,
        SectionEntity::class
   ],
    version = 1
)
abstract class ConfessionDatabase: RoomDatabase() {

    abstract fun chapterDao(): ChapterDao

    companion object {

        private const val DATABASE_NAME = "confession-db"

        // For Singleton instantiation
        @Volatile private var instance: ConfessionDatabase? = null

        fun getInstance(context: Context): ConfessionDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ConfessionDatabase {
            return Room.databaseBuilder(context, ConfessionDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<PopulateDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }

}