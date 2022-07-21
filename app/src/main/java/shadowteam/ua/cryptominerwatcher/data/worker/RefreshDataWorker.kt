package shadowteam.ua.cryptominerwatcher.data.worker

import android.content.Context
import android.util.Log
import androidx.work.*
import kotlinx.coroutines.delay
import shadowteam.ua.cryptominerwatcher.data.database.dao.CoinDao
import shadowteam.ua.cryptominerwatcher.data.maper.CoinMapper
import shadowteam.ua.cryptominerwatcher.data.network.ApiService
import javax.inject.Inject

class RefreshDataWorker(
    appContext: Context,
    params: WorkerParameters,
    private val coinDao: CoinDao,
    private val coinMapper: CoinMapper,
    private val apiService: ApiService
) : CoroutineWorker(appContext,
    params) {


    override suspend fun doWork(): Result {
        while (true){
            try {
                val topCoins = apiService.getTopCoinsList()
                val fSym = coinMapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getCoinFullInfo(fromSymbol = fSym)
                val listCoinInfoDto = coinMapper.mapJsonCoinInfoToListCoinInfo(jsonContainer)
                val dbModelList = listCoinInfoDto.map { coinMapper.mapDtoToDbModel(it) }
                coinDao.insertCoinInfoList(dbModelList)
                delay(REFRESH_DATA_TIME)
            }catch (e: Exception){
                Log.e("REFRESH_EXCEPTION", e.toString())
            }
        }
    }

    class Factory @Inject constructor(
        private val coinDao: CoinDao,
        private val coinMapper: CoinMapper,
        private val apiService: ApiService
    ) :ChildWorkerFactory{
        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return RefreshDataWorker(appContext, params, coinDao, coinMapper, apiService)
        }

    }

    companion object{
        private const val REFRESH_DATA_TIME = 10000L
        const val NAME_WORKER = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<RefreshDataWorker>()
                .build()
        }
    }
}