package shadowteam.ua.cryptominerwatcher.domain.usecase

import androidx.lifecycle.LiveData
import shadowteam.ua.cryptominerwatcher.domain.dataclass.CoinInfo
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.CoinRepository
import javax.inject.Inject

class GetCoinDetailCoinUseCase @Inject constructor( private val repository: CoinRepository) {

    operator fun invoke(fromSymbol:String) : LiveData<CoinInfo>  {
        return repository.getCoinDetail(fromSymbol)
    }
}