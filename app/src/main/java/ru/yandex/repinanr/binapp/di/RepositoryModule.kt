package ru.yandex.repinanr.binapp.di

import dagger.Binds
import dagger.Module
import ru.yandex.repinanr.binapp.domain.BinRepository
import ru.yandex.repinanr.binapp.domain.BinRepositoryImpl

@Module
interface RepositoryModule {

    @Binds
    @ApplicationScope
    fun bindsRepository(repositoryImpl: BinRepositoryImpl): BinRepository
}