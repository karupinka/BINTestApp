package ru.yandex.repinanr.binapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.yandex.repinanr.binapp.data.model.entity.BinHistoryEntity

@Database(
    entities = [
        BinHistoryEntity::class
    ],
    version = 1
)
abstract class BinAppDb: RoomDatabase() {

    abstract fun getHistoryDao(): BinHistoryDao

    companion object {
        private var DB: BinAppDb? = null
        private val LOCK = Any()
        private val DATABASE_NAME = "db-bin.db"

        fun getInstance(context: Context): BinAppDb {
            DB?.let { return it }

            val instance = Room.databaseBuilder(
                context,
                BinAppDb::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()

            DB = instance

            return instance
        }
    }
}