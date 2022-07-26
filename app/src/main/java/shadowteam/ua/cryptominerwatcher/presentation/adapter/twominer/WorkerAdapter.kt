package shadowteam.ua.cryptominerwatcher.presentation.adapter.twominer

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import shadowteam.ua.cryptominerwatcher.R
import shadowteam.ua.cryptominerwatcher.databinding.WorkerItemBinding
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Worker
import shadowteam.ua.cryptominerwatcher.presentation.adapter.utils.WorkersDifUtils
import javax.inject.Inject

class WorkerAdapter @Inject constructor(
    private val application: Application
):
    ListAdapter<Worker, WorkerViewHolder>(WorkersDifUtils()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val binding = WorkerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return WorkerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        val worker = getItem(position)
        with(holder.binding){
            with(worker){
                val hashTitle = application.resources.getString(R.string.hash_title)
                textViewWorker.text = name
                textViewCurrentHash.text = String.format(hashTitle, hrCurrent.toString())
                textViewAvgeHash.text = String.format(hashTitle, hrAvg.toString())
                textViewMinerHash.text = String.format(hashTitle, hrMiner.toString())
                textViewWorkerShare.text = lastShare.toString()
            }
        }
    }
}