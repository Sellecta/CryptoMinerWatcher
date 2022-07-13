package shadowteam.ua.cryptominerwatcher.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import shadowteam.ua.cryptominerwatcher.domain.dataclass.CoinInfo

class CoinInfoDifUtils : DiffUtil.ItemCallback<CoinInfo>() {
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }

}
