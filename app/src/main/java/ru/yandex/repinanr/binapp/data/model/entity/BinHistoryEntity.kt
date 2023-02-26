package ru.yandex.repinanr.binapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bins_history"
)
data class BinHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bin: String,
    @ColumnInfo(name = "card_type")
    val cardType: String,
    @ColumnInfo(name = "card_scheme")
    val cardScheme: String
)
