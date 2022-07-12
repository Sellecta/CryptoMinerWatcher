package shadowteam.ua.cryptominerwatcher.data.repository

import androidx.lifecycle.LiveData
import shadowteam.ua.cryptominerwatcher.domain.dataclass.CoinInfo
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.CoinRepository

class CoinRepositoryImpl: CoinRepository {
    override fun getCoinList(): LiveData<List<CoinInfo>> {
        TODO("Not yet implemented")
    }

    override fun getCoinDetail(fromSymbol: String): LiveData<CoinInfo> {
        TODO("Not yet implemented")
    }

    override fun loadData() {
        TODO("Not yet implemented")
    }


}
