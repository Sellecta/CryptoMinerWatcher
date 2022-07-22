package shadowteam.ua.cryptominerwatcher.domain.usecase.twominer

import shadowteam.ua.cryptominerwatcher.domain.domaininterface.twominer.TwoMinerRepository
import javax.inject.Inject

class SavePaymentCountUseCase @Inject constructor(private val repository: TwoMinerRepository) {

     suspend operator fun invoke(ip:String, wallet:String, count:Int = 5000000) = repository.savePaymentCountUseCase(ip, wallet, count)
}