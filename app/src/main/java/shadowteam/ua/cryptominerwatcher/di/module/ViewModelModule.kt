package shadowteam.ua.cryptominerwatcher.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shadowteam.ua.cryptominerwatcher.di.annotation.ViewModelKey
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.DetailViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    @Binds
    fun bindsDetailViewModel(impl : DetailViewModel):ViewModel
}