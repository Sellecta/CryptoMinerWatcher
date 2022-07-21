package shadowteam.ua.cryptominerwatcher.presentation.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.stats.StatsEvent
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import shadowteam.ua.cryptominerwatcher.R
import shadowteam.ua.cryptominerwatcher.data.database.AppDatabase
import shadowteam.ua.cryptominerwatcher.data.database.model.twominer.StatsDb
import shadowteam.ua.cryptominerwatcher.data.database.model.twominer.TwoMinerAccDb
import shadowteam.ua.cryptominerwatcher.data.database.model.twominer.WorkerDb
import shadowteam.ua.cryptominerwatcher.databinding.ActivityDetailBinding
import shadowteam.ua.cryptominerwatcher.presentation.adapter.TopCoinAdapter
import shadowteam.ua.cryptominerwatcher.presentation.application.CryptoMinerApplication
import shadowteam.ua.cryptominerwatcher.presentation.fragment.TwoMinerFragment
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.DetailViewModel
import shadowteam.ua.cryptominerwatcher.presentation.viewmodel.viewmodelfactory.ViewModelFactory
import java.sql.Types
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    private val component by lazy {
        (application as CryptoMinerApplication).component
    }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var coinAdapter : TopCoinAdapter

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
    }
    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragment = TwoMinerFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }

}