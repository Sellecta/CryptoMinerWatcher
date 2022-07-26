package shadowteam.ua.cryptominerwatcher.presentation.adapter.utils

import androidx.recyclerview.widget.DiffUtil
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.SumReward

class SumRewardDifUtils :DiffUtil.ItemCallback<SumReward>() {

    override fun areItemsTheSame(oldItem: SumReward, newItem: SumReward): Boolean {
        return oldItem.interval == newItem.interval
    }

    override fun areContentsTheSame(oldItem: SumReward, newItem: SumReward): Boolean {
        return  oldItem == newItem
    }
}