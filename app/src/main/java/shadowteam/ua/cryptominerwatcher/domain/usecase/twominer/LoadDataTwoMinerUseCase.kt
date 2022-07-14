package shadowteam.ua.cryptominerwatcher.domain.usecase.twominer

import shadowteam.ua.cryptominerwatcher.domain.domaininterface.twominer.TwoMinerRepository
import javax.inject.Inject

class LoadDataTwoMinerUseCase  @Inject constructor(private val repository: TwoMinerRepository){

    operator fun invoke(){
       repository.loadData()
    }
}