package shadowteam.ua.cryptominerwatcher.presentation.viewmodel.twominer

import androidx.lifecycle.ViewModel
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.GetTwoMinerSumRewardUseCase
import javax.inject.Inject

class SumRewardViewModel @Inject constructor(
    private val getTwoMinerSumRewardUseCase: GetTwoMinerSumRewardUseCase
): ViewModel() {

    val liveSumReward = getTwoMinerSumRewardUseCase()
}