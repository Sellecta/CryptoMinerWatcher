package shadowteam.ua.cryptominerwatcher.domain.domaininterface

interface CoinRepository {

    fun getCoinList()

    fun getCoinDetail()

    fun loadData()
}