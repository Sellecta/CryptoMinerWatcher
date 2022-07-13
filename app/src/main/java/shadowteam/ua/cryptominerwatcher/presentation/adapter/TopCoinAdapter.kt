package shadowteam.ua.cryptominerwatcher.presentation.adapter

import android.app.Application
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import shadowteam.ua.cryptominerwatcher.R
import shadowteam.ua.cryptominerwatcher.databinding.CardItemCoinBinding
import shadowteam.ua.cryptominerwatcher.domain.dataclass.CoinInfo
import javax.inject.Inject

class TopCoinAdapter @Inject constructor(
    private val application: Application
):ListAdapter<CoinInfo, CoinInfoViewHolder> (CoinInfoDifUtils()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = CardItemCoinBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int){
        val coin = getItem(position)
        with(holder.binding){
            with(coin){
                val symbolsTemplate = application.resources.getString(R.string.symbols_template)
                val percentTemplate = application.resources.getString(R.string.percent_template)
                textViewCoinPrice.text = price
                textViewCoinName.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                changePCT24Hour?.let {
                    if(changePCT24Hour < 0) { textViewPercentCoin.setTextColor(Color.RED) }
                    else { textViewPercentCoin.setTextColor(Color.GREEN)}
                }
                textViewPercentCoin.text = String.format(percentTemplate, changePCT24Hour)
                Picasso.get().load(imageUrl).into(imageViewCoinLogo)
            }
        }
    }
}