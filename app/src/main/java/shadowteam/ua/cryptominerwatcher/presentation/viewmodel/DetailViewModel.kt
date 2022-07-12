package shadowteam.ua.cryptominerwatcher.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import shadowteam.ua.cryptominerwatcher.domain.usecase.GetCoinDetailCoinUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.GetCoinListUseCase
import shadowteam.ua.cryptominerwatcher.domain.usecase.LoadDataUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getCoinDetailCoinUseCase: GetCoinDetailCoinUseCase,
    private val getCoinListUseCase: GetCoinListUseCase,
    private val loadDataUseCase: LoadDataUseCase
) :ViewModel() {

    val coinListLiveData = getCoinListUseCase()

    fun getDetailCoin(fSym:String) = getCoinDetailCoinUseCase(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}