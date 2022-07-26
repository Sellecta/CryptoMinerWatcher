package shadowteam.ua.cryptominerwatcher.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import shadowteam.ua.cryptominerwatcher.R
import shadowteam.ua.cryptominerwatcher.databinding.FragmentTwominerBinding
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.TwoMinerAcc
import shadowteam.ua.cryptominerwatcher.presentation.application.CryptoMinerApplication
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.twominer.TwoMinerViewModel
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.viewmodelfactory.ViewModelFactory
import javax.inject.Inject


class TwoMinerFragment : Fragment() {

    private var wallet: String? = null

    private val component by lazy{
        (requireActivity().application as CryptoMinerApplication)
            .component
    }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[TwoMinerViewModel::class.java]
    }

    private var _binding: FragmentTwominerBinding? = null
    private val binding: FragmentTwominerBinding
    get() = _binding ?: throw Exception("FragmentTwominerBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            wallet = it.getString(WALLET)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTwominerBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        viewModel.twoMinerAccLiveData.observe(viewLifecycleOwner){
            fillTextView(it)
        }
    }

    private fun fillTextView(acc:TwoMinerAcc){
        val usdTitleTemplate = resources.getString(R.string.price_usd_title)
        with(binding){
            textViewUnBalance.text = acc.stats.balance.toString()
            textViewTotalPaid.text = acc.stats.paid.toString()
            textViewToPaidBalance.text = acc.stats.immature.toString()
            textViewUnBalanceUsd.text =  String.format(usdTitleTemplate, acc.stats.immatureUSD)
            textViewToPaidUSD.text = String.format(usdTitleTemplate, acc.stats.balanceUsd)
            textViewTotalPaidUsd.text = String.format(usdTitleTemplate, acc.stats.paidUSD)
            progressBar.progress = acc.progress
            textViewPercentProgress.text = String.format(resources.getString(R.string.percent_progress), acc.progress)
            textViewLast24Reward.text = acc.reward24h.toString()
            textViewLats24RewardUsd.text = String.format(usdTitleTemplate, acc.reward24hUSD)
            textViewCurrentHashNumber.text = acc.currentHashrate.toString()
            textViewAvgHash.text = acc.hashrateAvg.toString()
            textViewMinerOnline.text = acc.workersOnline.toString()
            if(acc.workersOffline>0){
                textViewMinerOffline.text = String.format(resources.getString(R.string.offline_workers_title), acc.workersOffline)
            }else{ textViewMinerOffline.text = ""}
            Log.i("test1","Cikl_observer")
        }

    }
    companion object {

        private const val WALLET = "wallet"

        fun newInstance(wallet: String) =
            TwoMinerFragment().apply {
                arguments = Bundle().apply {
                    putString(WALLET, wallet)
                }
            }
        fun newInstance() = TwoMinerFragment()
    }
}