package shadowteam.ua.cryptominerwatcher.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import shadowteam.ua.cryptominerwatcher.R
import shadowteam.ua.cryptominerwatcher.databinding.FragmentWorkerBinding
import shadowteam.ua.cryptominerwatcher.presentation.adapter.twominer.WorkerAdapter
import shadowteam.ua.cryptominerwatcher.presentation.application.CryptoMinerApplication
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.twominer.WorkersViewModel
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.viewmodelfactory.ViewModelFactory
import javax.inject.Inject


class WorkerFragment : Fragment() {


    private val component by lazy {
        (requireActivity().application as CryptoMinerApplication)
            .component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var workerAdapter: WorkerAdapter

    private val viewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[WorkersViewModel::class.java]
    }

    private var _binding: FragmentWorkerBinding? = null
    private val binding: FragmentWorkerBinding
    get() = _binding ?: throw Exception("WorkersFragment == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWorkerBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        binding.workerRecycler.adapter = workerAdapter
        binding.workerRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.workerRecycler.itemAnimator = null
        viewModel.liveListWorker.observe(viewLifecycleOwner){
            workerAdapter.submitList(it)
        }
    }

    companion object {

        fun newInstance() = WorkerFragment()
    }
}
