package shadowteam.ua.cryptominerwatcher.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import shadowteam.ua.cryptominerwatcher.databinding.FragmentSumRewardBinding
import shadowteam.ua.cryptominerwatcher.presentation.adapter.twominer.SumRewardsAdapter
import shadowteam.ua.cryptominerwatcher.presentation.application.CryptoMinerApplication
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.twominer.SumRewardViewModel
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.viewmodelfactory.ViewModelFactory
import javax.inject.Inject

class RewardFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as CryptoMinerApplication)
            .component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SumRewardViewModel::class.java]
    }
    @Inject
    lateinit var sumRewardsAdapter: SumRewardsAdapter

    private var _binding: FragmentSumRewardBinding? = null
    private val binding: FragmentSumRewardBinding
    get() = _binding ?: throw Exception("SumRewardFragment = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSumRewardBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        binding.recyclerSumReward.adapter = sumRewardsAdapter
        binding.recyclerSumReward.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.recyclerSumReward.itemAnimator = null
        viewModel.liveSumReward.observe(viewLifecycleOwner){
            sumRewardsAdapter.submitList(it)
        }
    }

    companion object {
        fun newInstance() = RewardFragment()
    }
}