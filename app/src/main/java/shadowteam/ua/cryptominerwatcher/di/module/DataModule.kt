package shadowteam.ua.cryptominerwatcher.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import shadowteam.ua.cryptominerwatcher.data.network.ApiFactory
import shadowteam.ua.cryptominerwatcher.data.network.ApiService
import shadowteam.ua.cryptominerwatcher.data.repository.CoinRepositoryImpl
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.CoinRepository

@Module
interface DataModule {

    @Binds
    fun bindRepository(impl: CoinRepositoryImpl) : CoinRepository

    companion object{

        @Provides
        fun provideApiService() : ApiService{
            return ApiFactory.apiService
        }
    }
}