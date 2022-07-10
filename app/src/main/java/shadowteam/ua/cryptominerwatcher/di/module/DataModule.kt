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
import shadowteam.ua.cryptominerwatcher.di.annotation.AppScope
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.CoinRepository

@Module
interface DataModule {

    @Binds
    @AppScope
    fun bindRepository(impl: CoinRepositoryImpl) : CoinRepository

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