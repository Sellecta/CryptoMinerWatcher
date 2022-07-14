package shadowteam.ua.cryptominerwatcher.domain.usecase.coin

import androidx.lifecycle.LiveData
import shadowteam.ua.cryptominerwatcher.domain.dataclass.coin.CoinInfo
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.coin.CoinRepository
import javax.inject.Inject

class GetCoinDetailCoinUseCase @Inject constructor( private val repository: CoinRepository) {

    operator fun invoke(fromSymbol:String) : LiveData<CoinInfo>  {
        return repository.getCoinDetail(fromSymbol)
    }
}