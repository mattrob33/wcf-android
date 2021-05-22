package com.mattrobertson.wcf.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mattrobertson.wcf.data.db.model.ChapterEntity
import com.mattrobertson.wcf.data.db.model.ConfessionEntity

@Dao
interface ConfessionDao {

    @Query("SELECT * FROM confession")
    suspend fun getConfession(): ConfessionEntity

    @Insert
    suspend fun insert(confession: ConfessionEntity)

    @Delete
    suspend fun delete(confession: ConfessionEntity)

}