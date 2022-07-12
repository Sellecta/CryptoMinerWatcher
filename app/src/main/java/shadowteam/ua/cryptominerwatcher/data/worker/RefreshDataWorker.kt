package shadowteam.ua.cryptominerwatcher.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import shadowteam.ua.cryptominerwatcher.data.database.CryptoMinerDao
import shadowteam.ua.cryptominerwatcher.data.maper.CoinMapper
import shadowteam.ua.cryptominerwatcher.data.network.ApiService

class RefreshDataWorker(
    appContext: Context,
    params: WorkerParameters,
    private val cryptoMinerDao: CryptoMinerDao,
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
                cryptoMinerDao.insertCoinInfoList(dbModelList)
                delay(REFRESH_DATA_TIME)
            }catch (e: Exception){
                Log.e("REFRESH_EXCEPTION", e.toString())
            }
        }
    }

    companion object{
        private const val REFRESH_DATA_TIME = 10000L
    }
}