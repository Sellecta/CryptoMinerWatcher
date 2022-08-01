package shadowteam.ua.cryptominerwatcher.presentation.viewmodel.twominer

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.GetTwoMinerConfigAccUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.SavePaymentCountUseCase
import javax.inject.Inject

class ConfigViewModel @Inject constructor(
    private val getTwoMinerConfigAccUseCase: GetTwoMinerConfigAccUseCase,
    private val savePaymentCountUseCase: SavePaymentCountUseCase
): ViewModel() {
    val liveTwoMinerConfig = getTwoMinerConfigAccUseCase()

    private var _liveRequestCode = MutableLiveData<Int>()
    val liveRequestCode :LiveData<Int>
    get() = _liveRequestCode


    fun saveMinPay(ip:String, count:Int, wallet:String){
        viewModelScope.launch {
            val code = savePaymentCountUseCase(ip, wallet,count)
            _liveRequestCode.postValue(code)
        }
    }

}