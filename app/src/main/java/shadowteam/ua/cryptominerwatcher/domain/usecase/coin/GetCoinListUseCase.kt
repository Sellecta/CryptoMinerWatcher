package shadowteam.ua.cryptominerwatcher.domain.usecase.coin

import shadowteam.ua.cryptominerwatcher.domain.domaininterface.coin.CoinRepository
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(private val repository: CoinRepository){

    operator fun invoke() = repository.getCoinList()

}