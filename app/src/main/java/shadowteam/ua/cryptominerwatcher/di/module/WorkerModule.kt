package shadowteam.ua.cryptominerwatcher.di.module

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shadowteam.ua.cryptominerwatcher.data.worker.ChildWorkerFactory
import shadowteam.ua.cryptominerwatcher.data.worker.RefreshDataWorker
import shadowteam.ua.cryptominerwatcher.di.annotation.WorkerKey

@Module
interface WorkerModule {

    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    @Binds
    fun bindRefreshDataWorkerFactory(impl: RefreshDataWorker.Factory):ChildWorkerFactory
}