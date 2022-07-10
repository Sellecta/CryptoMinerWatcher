package shadowteam.ua.cryptominerwatcher.domain.usecase

import shadowteam.ua.cryptominerwatcher.domain.domaininterface.CoinRepository
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(private val repository: CoinRepository){

    fun invoke(){
        repository.getCoinList()
    }
}