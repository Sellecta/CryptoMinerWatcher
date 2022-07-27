package shadowteam.ua.cryptominerwatcher.domain.usecase.twominer

import shadowteam.ua.cryptominerwatcher.domain.domaininterface.twominer.TwoMinerRepository
import javax.inject.Inject

class GetTwoMinerAccUseCase @Inject constructor(private val repository: TwoMinerRepository) {

     suspend operator  fun invoke() = repository.getTwoMinerAcc()
}