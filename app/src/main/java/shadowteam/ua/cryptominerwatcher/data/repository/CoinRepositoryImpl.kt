package shadowteam.ua.cryptominerwatcher.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import shadowteam.ua.cryptominerwatcher.data.database.CryptoMinerDao
import shadowteam.ua.cryptominerwatcher.data.maper.CoinMapper
import shadowteam.ua.cryptominerwatcher.domain.dataclass.CoinInfo
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val cryptoMinerDao: CryptoMinerDao,
    private val coinMapper: CoinMapper
) : CoinRepository {

    override fun getCoinList(): LiveData<List<CoinInfo>> {
        return Transformations.map(cryptoMinerDao.getAllCoins()){  listCoin ->
            listCoin.map {
                coinMapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinDetail(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(cryptoMinerDao.getInfoAboutCoin(fromSymbol)){
            coinMapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        TODO("Not yet implemented")
    }


}
