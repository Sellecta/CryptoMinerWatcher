package shadowteam.ua.cryptominerwatcher.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import shadowteam.ua.cryptominerwatcher.data.database.dao.CoinDao
import shadowteam.ua.cryptominerwatcher.data.maper.CoinMapper
import shadowteam.ua.cryptominerwatcher.data.worker.RefreshDataWorker
import shadowteam.ua.cryptominerwatcher.domain.dataclass.coin.CoinInfo
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.coin.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinDao: CoinDao,
    private val coinMapper: CoinMapper,
    private val application: Application
) : CoinRepository {

    override fun getCoinList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinDao.getAllCoins()){ listCoin ->
            listCoin.map {
                coinMapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinDetail(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinDao.getInfoAboutCoin(fromSymbol)){
            coinMapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val worker = WorkManager.getInstance(application)
        worker.enqueueUniqueWork(
            RefreshDataWorker.NAME_WORKER,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }


}
