package shadowteam.ua.cryptominerwatcher.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shadowteam.ua.cryptominerwatcher.di.annotation.ViewModelKey
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.coin.DetailViewModel
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.twominer.TwoMinerViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    @Binds
    fun bindsDetailViewModel(impl : DetailViewModel):ViewModel

    @IntoMap
    @ViewModelKey(TwoMinerViewModel::class)
    @Binds
    fun bindsTwoMinerViewModel(impl: TwoMinerViewModel):ViewModel
}