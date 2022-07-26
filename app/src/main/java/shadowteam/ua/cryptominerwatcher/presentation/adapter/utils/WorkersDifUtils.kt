package shadowteam.ua.cryptominerwatcher.presentation.adapter.utils

import androidx.recyclerview.widget.DiffUtil
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Worker

class WorkersDifUtils : DiffUtil.ItemCallback<Worker>() {
    override fun areItemsTheSame(oldItem: Worker, newItem: Worker): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Worker, newItem: Worker): Boolean {
        return  oldItem == newItem
    }
}