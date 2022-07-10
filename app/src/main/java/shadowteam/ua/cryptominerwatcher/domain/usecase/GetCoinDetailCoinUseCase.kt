package shadowteam.ua.cryptominerwatcher.domain.usecase

import shadowteam.ua.cryptominerwatcher.domain.domaininterface.CoinRepository
import javax.inject.Inject

class GetCoinDetailCoinUseCase @Inject constructor( private val repository: CoinRepository) {

    fun invoke(){
        repository.getCoinDetail()
    }
}