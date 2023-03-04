package ru.yandex.repinanr.binapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.yandex.repinanr.binapp.presentation.history.HistoryFragment
import ru.yandex.repinanr.binapp.presentation.info.BinInfoFragment

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: HistoryFragment)

    fun inject(fragment: BinInfoFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}