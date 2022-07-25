package shadowteam.ua.cryptominerwatcher.presentation.viewmodel.coin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import shadowteam.ua.cryptominerwatcher.data.network.ApiService
import shadowteam.ua.cryptominerwatcher.domain.usecase.coin.GetCoinDetailCoinUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.coin.GetCoinListUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.coin.LoadDataUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.LoadDataTwoMinerUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.SavePaymentCountUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getCoinDetailCoinUseCase: GetCoinDetailCoinUseCase,
    private val getCoinListUseCase: GetCoinListUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val loadDataTwoMinerUseCase: LoadDataTwoMinerUseCase,
    private val savePaymentCountUseCase: SavePaymentCountUseCase
) :ViewModel() {

    val coinListLiveData = getCoinListUseCase()

    fun getDetailCoin(fSym:String) = getCoinDetailCoinUseCase(fSym)


    init {
        viewModelScope.launch {
            loadDataUseCase()
            loadDataTwoMinerUseCase()
        }
    }
}