package shadowteam.ua.cryptominerwatcher.data.worker.twominer

import android.content.Context
import android.util.Log
import androidx.work.*
import kotlinx.coroutines.delay
import shadowteam.ua.cryptominerwatcher.data.database.dao.TwoMinerDao
import shadowteam.ua.cryptominerwatcher.data.maper.TwoMinerMapper
import shadowteam.ua.cryptominerwatcher.data.network.ApiService
import shadowteam.ua.cryptominerwatcher.data.worker.ChildWorkerFactory
import javax.inject.Inject

class TwoMinerDataWorker(
    appContext: Context,
    private val params: WorkerParameters,
    private val apiService: ApiService,
    private val twoMinerMapper: TwoMinerMapper,
    private val twoMinerDao: TwoMinerDao,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        try {
            while (true) {
                val wallet = params.inputData.getString(WALLET)?.let { walletTwoMiner ->
                    val twoMinerAccDto = apiService.getTwoMinersAccount(walletTwoMiner)
                    val twoMinerAccDb = twoMinerMapper.mapTwoMinerAccDtoToTwoMinerAccDb(
                        twoMinerAccDto,
                        walletTwoMiner)
                    val configAccDb =
                        twoMinerMapper.mapConfigAccDtoToConfigAccDb(twoMinerAccDto.config,
                            walletTwoMiner)
                    val listPaymentDb = twoMinerMapper.mapListPaymentDtoToListPaymentDb(
                        twoMinerAccDto.payments,
                        walletTwoMiner)
                    val listSumRewardDb = twoMinerMapper.mapListSumRewardDToListSumRewardDb(
                        twoMinerAccDto.sumrewards,
                        walletTwoMiner)
                    val listWorkerDb =
                        twoMinerMapper.mapJsonObjectWorkerToWorkerDb(twoMinerAccDto.workers,
                            walletTwoMiner)
                    twoMinerDao.insertAllDataTwoMiner(listWorkerDb,
                        listPaymentDb,
                        listSumRewardDb,
                        twoMinerAccDb,
                        configAccDb)
                    delay(REFRESH_DELAY)
                }
            }
        } catch (e: Exception) {
            Log.e("LOG_ERROR", e.toString())
            return Result.retry()
        }
    }


    class Factory @Inject constructor(
        private val apiService: ApiService,
        private val twoMinerMapper: TwoMinerMapper,
        private val twoMinerDao: TwoMinerDao,
    ) : ChildWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return TwoMinerDataWorker(appContext, params, apiService, twoMinerMapper, twoMinerDao)
        }
    }

    companion object {
        const val Name = "TwoMinerWorker"
        private const val WALLET = "wallet"
        private const val REFRESH_DELAY = 30000L

        fun makeRequest(wallet: String): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<TwoMinerDataWorker>()
                .setInputData(workDataOf(WALLET to wallet))
                .build()
        }
    }
}