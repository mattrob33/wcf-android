package com.mattrobertson.wcf.data.db.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mattrobertson.wcf.data.db.ConfessionDatabase
import com.mattrobertson.wcf.data.db.mappers.mapConfession
import com.mattrobertson.wcf.data.json.ConfessionParser
import kotlinx.coroutines.coroutineScope

class PopulateDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val jsonConfession = ConfessionParser().jsonConfession
            val confessionEntity = mapConfession(jsonConfession)

            val database = ConfessionDatabase.getInstance(applicationContext)
            database.confessionDao().insert(confessionEntity)

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