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
        viewModel._twoMinerAccLiveData.observe(viewLifecycleOwner){
            with(binding){
                textViewUnBalance.text = it.stats.balance.toString()
                textViewTotalPaid.text = it.stats.paid.toString()
                textViewToPaidBalance.text = it.stats.immature.toString()
                textViewUnBalanceUsd.text = it.stats.immatureUSD.toString()
                textViewToPaidUSD.text = it.stats.balanceUsd.toString()
                textViewTotalPaidUsd.text = it.stats.paidUSD.toString()
            }
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