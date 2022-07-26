package shadowteam.ua.cryptominerwatcher.presentation.layout

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import shadowteam.ua.cryptominerwatcher.R
import shadowteam.ua.cryptominerwatcher.databinding.ActivityDetailBinding
import shadowteam.ua.cryptominerwatcher.presentation.adapter.coin.TopCoinAdapter
import shadowteam.ua.cryptominerwatcher.presentation.application.CryptoMinerApplication
import shadowteam.ua.cryptominerwatcher.presentation.fragment.TwoMinerFragment
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.coin.DetailViewModel
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.viewmodelfactory.ViewModelFactory
import javax.inject.Inject


class DetailActivity : AppCompatActivity() {

    private val component by lazy {
        (application as CryptoMinerApplication).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var coinAdapter: TopCoinAdapter

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
    }
    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        binding.recyclerTopCoin.adapter = coinAdapter
        binding.recyclerTopCoin.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerTopCoin.itemAnimator = null
        viewModel.coinListLiveData.observe(this) {
            coinAdapter.submitList(it)
        }
        val time = (System.currentTimeMillis()/1000) - 1659175864
        Log.i("test1", time.toString())
        binding.walletField.setEndIconOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerMiners, TwoMinerFragment.newInstance())
                .commit()
        }
    }
}