package shadowteam.ua.cryptominerwatcher.domain.domaininterface

import androidx.lifecycle.LiveData
import shadowteam.ua.cryptominerwatcher.domain.dataclass.CoinInfo

interface CoinRepository {

    fun getCoinList():LiveData<List<CoinInfo>>

    fun getCoinDetail(fromSymbol :String) : LiveData<CoinInfo>

    fun loadData()
}