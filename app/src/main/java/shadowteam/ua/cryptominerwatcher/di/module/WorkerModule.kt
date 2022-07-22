package shadowteam.ua.cryptominerwatcher.di.module

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shadowteam.ua.cryptominerwatcher.data.worker.ChildWorkerFactory
import shadowteam.ua.cryptominerwatcher.data.worker.coin.CoinDataWorker
import shadowteam.ua.cryptominerwatcher.data.worker.twominer.TwoMinerDataWorker
import shadowteam.ua.cryptominerwatcher.di.annotation.WorkerKey

@Module
interface WorkerModule {

    @IntoMap
    @WorkerKey(CoinDataWorker::class)
    @Binds
    fun bindRefreshDataWorkerFactory(impl: CoinDataWorker.Factory):ChildWorkerFactory

    @IntoMap
    @WorkerKey(TwoMinerDataWorker::class)
    @Binds
    fun binTwoMinerDataWorker(impl: TwoMinerDataWorker.Factory):ChildWorkerFactory
}