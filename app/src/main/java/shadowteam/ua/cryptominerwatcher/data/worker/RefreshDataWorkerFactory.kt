package shadowteam.ua.cryptominerwatcher.data.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import shadowteam.ua.cryptominerwatcher.data.worker.coin.CoinDataWorker
import shadowteam.ua.cryptominerwatcher.data.worker.twominer.TwoMinerDataWorker
import javax.inject.Inject
import javax.inject.Provider

class RefreshDataWorkerFactory @Inject constructor(
    private val workerProvides: @JvmSuppressWildcards
    Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return when (workerClassName) {
            CoinDataWorker::class.qualifiedName -> {
                val childWorkerFactory = workerProvides[CoinDataWorker::class.java]?.get()
                return childWorkerFactory?.create(appContext, workerParameters)
            }
            TwoMinerDataWorker::class.qualifiedName->{
                val childWorkerFactory = workerProvides[TwoMinerDataWorker::class.java]?.get()
                return childWorkerFactory?.create(appContext, workerParameters)
            }
            else -> null
        }
    }
}