package shadowteam.ua.cryptominerwatcher.domain.usecase

import shadowteam.ua.cryptominerwatcher.domain.domaininterface.CoinRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke() {
     repository.loadData()
    }
}