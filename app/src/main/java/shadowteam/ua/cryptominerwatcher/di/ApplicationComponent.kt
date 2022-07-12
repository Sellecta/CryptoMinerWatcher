package shadowteam.ua.cryptominerwatcher.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import shadowteam.ua.cryptominerwatcher.di.annotation.AppScope
import shadowteam.ua.cryptominerwatcher.di.module.DataModule
import shadowteam.ua.cryptominerwatcher.di.module.ViewModelModule
import shadowteam.ua.cryptominerwatcher.presentation.layout.DetailActivity

@AppScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent  {

    fun inject(detailActivity: DetailActivity)

    @Component.Factory
    interface ApplicationComponentFactory{

        fun create(
            @BindsInstance application: Application
        ):ApplicationComponent
    }
}