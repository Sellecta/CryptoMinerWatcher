package shadowteam.ua.cryptominerwatcher.presentation.application

import android.app.Application
import shadowteam.ua.cryptominerwatcher.di.DaggerApplicationComponent

class CryptoMinerApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}