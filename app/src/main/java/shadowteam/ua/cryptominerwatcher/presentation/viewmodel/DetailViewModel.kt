package shadowteam.ua.cryptominerwatcher.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import shadowteam.ua.cryptominerwatcher.data.network.ApiService
import shadowteam.ua.cryptominerwatcher.domain.usecase.coin.GetCoinDetailCoinUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.coin.GetCoinListUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.coin.LoadDataUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getCoinDetailCoinUseCase: GetCoinDetailCoinUseCase,
    private val getCoinListUseCase: GetCoinListUseCase,
    private val loadDataUseCase: LoadDataUseCase,
) :ViewModel() {

    val coinListLiveData = getCoinListUseCase()

    fun getDetailCoin(fSym:String) = getCoinDetailCoinUseCase(fSym)


    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}