package ru.yandex.repinanr.binapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bins_history"
)
data class BinHistoryEntity(
    @PrimaryKey
    val id: Long,
    val bin: String,
    @ColumnInfo(name = "column_info")
    val cardType: String
)
