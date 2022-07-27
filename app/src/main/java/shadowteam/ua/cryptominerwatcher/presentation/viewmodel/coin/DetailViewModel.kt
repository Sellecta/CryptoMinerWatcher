package shadowteam.ua.cryptominerwatcher.presentation.viewmodel.coin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import shadowteam.ua.cryptominerwatcher.data.network.ApiService
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.TwoMinerAcc
import shadowteam.ua.cryptominerwatcher.domain.usecase.coin.GetCoinDetailCoinUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.coin.GetCoinListUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.coin.LoadDataUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.GetTwoMinerAccUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.LoadDataTwoMinerUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.SavePaymentCountUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getCoinDetailCoinUseCase: GetCoinDetailCoinUseCase,
    private val getCoinListUseCase: GetCoinListUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val loadDataTwoMinerUseCase: LoadDataTwoMinerUseCase,
    private val savePaymentCountUseCase: SavePaymentCountUseCase,
    private val getTwoMinerAccUseCase: GetTwoMinerAccUseCase
) :ViewModel() {

    val coinListLiveData = getCoinListUseCase()

    fun getDetailCoin(fSym:String) = getCoinDetailCoinUseCase(fSym)

    private var _twoMinerAccLiveData  = MutableLiveData<TwoMinerAcc>()
    val twoMinerAccLiveData: LiveData<TwoMinerAcc>
        get(){
            viewModelScope.launch {
                _twoMinerAccLiveData = getTwoMinerAccUseCase() as MutableLiveData<TwoMinerAcc>
            }
            return _twoMinerAccLiveData
        }


    init {
        viewModelScope.launch {
            loadDataUseCase()
            loadDataTwoMinerUseCase()
        }
    }
}