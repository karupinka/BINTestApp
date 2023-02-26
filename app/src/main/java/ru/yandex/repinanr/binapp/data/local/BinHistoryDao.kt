package ru.yandex.repinanr.binapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.yandex.repinanr.binapp.data.model.entity.BinHistoryEntity

@Dao
interface BinHistoryDao {

    @Query("DELETE FROM bins_history")
    suspend fun clearHistory()

    @Query("SELECT * FROM bins_history")
    suspend fun getHistory(): List<BinHistoryEntity>

    @Insert(onConflict = REPLACE)
    suspend fun addHistoryItem(binHistoryItem: BinHistoryEntity)
}
