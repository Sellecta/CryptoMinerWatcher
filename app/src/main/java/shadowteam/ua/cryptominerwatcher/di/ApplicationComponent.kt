package shadowteam.ua.cryptominerwatcher.di

import android.app.Application
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import shadowteam.ua.cryptominerwatcher.di.annotation.AppScope
import shadowteam.ua.cryptominerwatcher.di.module.DataModule
import shadowteam.ua.cryptominerwatcher.di.module.ViewModelModule
import shadowteam.ua.cryptominerwatcher.di.module.WorkerModule
import shadowteam.ua.cryptominerwatcher.presentation.application.CryptoMinerApplication
import shadowteam.ua.cryptominerwatcher.presentation.fragment.ConfigFragment
import shadowteam.ua.cryptominerwatcher.presentation.fragment.RewardFragment
import shadowteam.ua.cryptominerwatcher.presentation.fragment.TwoMinerFragment
import shadowteam.ua.cryptominerwatcher.presentation.fragment.WorkerFragment
import shadowteam.ua.cryptominerwatcher.presentation.layout.DetailActivity

@AppScope
@Component(modules = [DataModule::class, ViewModelModule::class, WorkerModule::class])
interface ApplicationComponent  {

    fun inject(detailActivity: DetailActivity)

    fun inject(cryptoMinerApplication: CryptoMinerApplication)

    fun inject(twiMinerFragment: TwoMinerFragment)

    fun inject(workerFragment: WorkerFragment)

    fun inject(rewardFragment: RewardFragment)

    fun inject(configFragment: ConfigFragment)

    @Component.Factory
    interface ApplicationComponentFactory{

        fun create(
            @BindsInstance application: Application
        ):ApplicationComponent
    }
}