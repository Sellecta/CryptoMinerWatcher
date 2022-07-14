package shadowteam.ua.cryptominerwatcher.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import shadowteam.ua.cryptominerwatcher.data.database.AppDatabase
import shadowteam.ua.cryptominerwatcher.data.database.CryptoMinerDao
import shadowteam.ua.cryptominerwatcher.data.network.ApiFactory
import shadowteam.ua.cryptominerwatcher.data.network.ApiService
import shadowteam.ua.cryptominerwatcher.data.repository.CoinRepositoryImpl
import shadowteam.ua.cryptominerwatcher.data.repository.TwoMinerRepositoryImpl
import shadowteam.ua.cryptominerwatcher.di.annotation.AppScope
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.coin.CoinRepository
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.twominer.TwoMinerRepository

@Module
interface DataModule {

    @Binds
    @AppScope
    fun bindRepositoryCoin(impl: CoinRepositoryImpl) : CoinRepository

    @Binds
    @AppScope
    fun bindRepositoryTwoMiners(impl: TwoMinerRepositoryImpl): TwoMinerRepository

    companion object{

        @AppScope
        @Provides
        fun provideApiService() : ApiService{
            return ApiFactory.apiService
        }

        @Provides
        @AppScope
        fun provideCryptoMinerDao(application: Application):CryptoMinerDao{
            return AppDatabase.getInstance(application).cryptoMinerDao()
        }
    }
}