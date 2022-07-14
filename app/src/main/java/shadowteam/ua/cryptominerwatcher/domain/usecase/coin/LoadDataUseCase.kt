package shadowteam.ua.cryptominerwatcher.domain.usecase.coin

import shadowteam.ua.cryptominerwatcher.domain.domaininterface.coin.CoinRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke() {
     repository.loadData()
    }
}