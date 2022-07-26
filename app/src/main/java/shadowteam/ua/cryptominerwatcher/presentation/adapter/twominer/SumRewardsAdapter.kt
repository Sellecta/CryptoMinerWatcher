package shadowteam.ua.cryptominerwatcher.presentation.adapter.twominer

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import shadowteam.ua.cryptominerwatcher.R
import shadowteam.ua.cryptominerwatcher.databinding.PeriodItemBinding
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.SumReward
import shadowteam.ua.cryptominerwatcher.presentation.adapter.utils.SumRewardDifUtils
import javax.inject.Inject

class SumRewardsAdapter @Inject constructor(
    private val application: Application
):ListAdapter<SumReward, SumRewardViewHolder>(SumRewardDifUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SumRewardViewHolder {
        val binding  = PeriodItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return SumRewardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SumRewardViewHolder, position: Int) {
        val sumReward = getItem(position)
        with(holder.binding){
            with(sumReward){
                val usdTemplate = application.getString(R.string.title_usd_sumReward)
                textViewTitleTime.text = name
                textViewTitleEthCount.text = rewardETH.toString()
                textViewTitleBtcCount.text = rewardBTC.toString()
                textViewTitleUsdCount.text = String.format(usdTemplate, rewardUSD)
            }
        }
    }
}