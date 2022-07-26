package shadowteam.ua.cryptominerwatcher.presentation.viewmodel.twominer

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import shadowteam.ua.cryptominerwatcher.data.database.model.twominer.WorkerDb
import shadowteam.ua.cryptominerwatcher.data.maper.TwoMinerMapper
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.TwoMinerAcc
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.GetTwoMinerAccUseCase
import javax.inject.Inject

class TwoMinerViewModel @Inject constructor(
    private val getTwoMinerAccUseCase: GetTwoMinerAccUseCase
) :ViewModel() {

    private var _twoMinerAccLiveData  = MutableLiveData<TwoMinerAcc>()
    val twoMinerAccLiveData: LiveData<TwoMinerAcc>
    get(){
        viewModelScope.launch {
            _twoMinerAccLiveData = getTwoMinerAccUseCase() as MutableLiveData<TwoMinerAcc>
        }
        return _twoMinerAccLiveData
    }

}