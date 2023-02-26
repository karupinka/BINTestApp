package ru.yandex.repinanr.binapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.yandex.repinanr.binapp.data.local.BinAppDb
import ru.yandex.repinanr.binapp.data.local.BinHistoryDao
import ru.yandex.repinanr.binapp.data.remote.ApiClient
import ru.yandex.repinanr.binapp.data.remote.BinService

@Module
interface DataModule {

    companion object {
        @Provides
        @ApplicationScope
        fun bindsService(): BinService {
            return ApiClient.apiService
        }

        @Provides
        @ApplicationScope
        fun bindsHistoryDao(application: Application): BinHistoryDao {
            return BinAppDb.getInstance(application).getHistoryDao()
        }
    }
}