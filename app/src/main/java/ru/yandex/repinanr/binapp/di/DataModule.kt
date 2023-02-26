package ru.yandex.repinanr.binapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.yandex.repinanr.binapp.data.remote.ApiClient

@Module
interface DataModule {

    companion object {
        @Provides
        @ApplicationScope
        fun bindsService() = ApiClient.apiService
    }
}