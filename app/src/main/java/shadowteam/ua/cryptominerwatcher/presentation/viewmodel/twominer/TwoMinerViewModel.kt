package shadowteam.ua.cryptominerwatcher.presentation.viewmodel.twominer

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.TwoMinerAcc
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.GetTwoMinerAccUseCase
import javax.inject.Inject

class TwoMinerViewModel @Inject constructor(
    private val getTwoMinerAccUseCase: GetTwoMinerAccUseCase
) :ViewModel() {

     var _twoMinerAccLiveData  = MutableLiveData<TwoMinerAcc>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _twoMinerAccLiveData.postValue(getTwoMinerAccUseCase()) }

    }
}