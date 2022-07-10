package shadowteam.ua.cryptominerwatcher.di.module

import dagger.Binds
import dagger.Module
import shadowteam.ua.cryptominerwatcher.data.repository.CoinRepositoryImpl
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.CoinRepository

@Module
interface DataModule {

    @Binds
    fun bindRepository(impl: CoinRepositoryImpl) : CoinRepository

}