package ru.yandex.repinanr.binapp.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.yandex.repinanr.binapp.presentation.history.HistoryViewModel
import ru.yandex.repinanr.binapp.presentation.info.BinInfoViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    @Binds
    fun bindHistoryViewModel(model: HistoryViewModel): ViewModel

    @IntoMap
    @ViewModelKey(BinInfoViewModel::class)
    @Binds
    fun bindBinInfoViewModel(model: BinInfoViewModel): ViewModel
}