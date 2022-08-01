package shadowteam.ua.cryptominerwatcher.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_config.*
import shadowteam.ua.cryptominerwatcher.R
import shadowteam.ua.cryptominerwatcher.databinding.FragmentConfigBinding
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.ConfigAcc
import shadowteam.ua.cryptominerwatcher.presentation.application.CryptoMinerApplication
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.twominer.ConfigViewModel
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.viewmodelfactory.ViewModelFactory
import javax.inject.Inject


class ConfigFragment : Fragment() {

    private val component by lazy{
        (requireActivity().application as CryptoMinerApplication)
            .component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ConfigViewModel::class.java]
    }
    private var _binding: FragmentConfigBinding? = null
    private val binding: FragmentConfigBinding
    get() = _binding ?: throw Exception("ConfigFragment == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentConfigBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        viewModel.liveTwoMinerConfig.observe(viewLifecycleOwner) {
            initViewConfig(it)
        }
        viewModel.liveRequestCode.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.buttonSaveConfig.setOnClickListener {
            val ip = editTextIP.text.toString()
            val countTemp = editTextMinPay.text.toString().toDouble()
            val count = (countTemp * NUM_MULTIPLIER_PAY).toInt()
            viewModel.saveMinPay(ip = ip,count = count, "bc1q99ymugekddfemgaw4nzptv45xdvz7k797d3qpc")
            editTextIP.text.clear()
            editTextMinPay.text.clear()
            TODO("need implement check error input")
        }
    }

    private fun initViewConfig(it: ConfigAcc) {
        val titleMinPay = getString(R.string.config_minPay_title)
        with(binding){
            textViewWorkerTitle.text = it.ipWorkerName
            editTextMinPay.hint = it.minPayout.toString()
            textViewPayCountTitle.text = String.format(titleMinPay, it.minPayoutUSD, it.minPayoutBTC)

        }
    }

    companion object {
        private const val NUM_MULTIPLIER_PAY = 1_000_000_000
        fun newInstance() = ConfigFragment()
    }
}