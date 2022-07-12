package shadowteam.ua.cryptominerwatcher.presentation.application

import android.app.Application
import androidx.work.Configuration
import shadowteam.ua.cryptominerwatcher.data.worker.RefreshDataWorker
import shadowteam.ua.cryptominerwatcher.data.worker.RefreshDataWorkerFactory
import shadowteam.ua.cryptominerwatcher.di.DaggerApplicationComponent
import javax.inject.Inject

class CryptoMinerApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var  refreshDataWorkerFactory: RefreshDataWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(refreshDataWorkerFactory)
            .build()
    }

}