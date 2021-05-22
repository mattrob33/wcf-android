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
    fun getConfession(): ConfessionEntity

    @Insert
    fun insert(confession: ConfessionEntity)

    @Delete
    fun delete(confession: ConfessionEntity)

}