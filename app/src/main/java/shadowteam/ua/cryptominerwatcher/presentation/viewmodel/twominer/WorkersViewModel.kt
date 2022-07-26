package shadowteam.ua.cryptominerwatcher.presentation.viewmodel.twominer

import androidx.lifecycle.ViewModel
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.GetTwoMinerWorkerUseCase
import javax.inject.Inject

class WorkersViewModel @Inject constructor(
    private val getTwoMinerWorkerUseCase: GetTwoMinerWorkerUseCase
) :ViewModel(){
    val liveListWorker = getTwoMinerWorkerUseCase()
}