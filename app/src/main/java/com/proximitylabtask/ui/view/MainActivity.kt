package com.proximitylabtask.ui.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.proximitylabtask.R
import com.proximitylabtask.data.remote.api.WebServicesProvider
import com.proximitylabtask.data.repo.MainRepository
import com.proximitylabtask.databinding.ActivityMainBinding
import com.proximitylabtask.ui.adapter.AirQualityAdapter
import com.proximitylabtask.ui.viewmodel.MainViewModel
import com.proximitylabtask.ui.viewmodel.factory.MainViewModelFactory
import com.proximitylabtask.utils.Util

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory(MainRepository(WebServicesProvider()))
    }
    private lateinit var binding: ActivityMainBinding
    private var adapter: AirQualityAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUi()
        if (Util.isNetworkAvailable(this)) {
            initObservers()
        } else {
            binding.progressBar.visibility = View.GONE
            binding.tvErrorMessage.visibility = View.VISIBLE
            binding.tvErrorMessage.text = getString(R.string.please_check_internet_connection)
        }

        viewModel.subscribeToSocketEvents()
    }

    private fun initUi() {
        adapter = AirQualityAdapter()
        binding.rvAqi.layoutManager = LinearLayoutManager(this)
        binding.rvAqi.adapter = adapter

        binding.includeHead.tvCity.setTypeface(null, Typeface.BOLD);
        binding.includeHead.tvAqi.setTypeface(null, Typeface.BOLD);
        binding.includeHead.tvTime.setTypeface(null, Typeface.BOLD);

    }

    private fun initObservers() {
        viewModel.loadingLiveData.observe(this, Observer {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            binding.rvAqi.visibility = if (it) View.GONE else View.VISIBLE
        })

        viewModel.airQualityList.observe(this, Observer {
            it?.let { list ->
                if (list == null || list.isEmpty()) {
                    binding.progressBar.visibility = View.GONE
                    binding.tvErrorMessage.visibility = View.VISIBLE
                    binding.tvErrorMessage.text = getString(R.string.no_data)

                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.tvErrorMessage.visibility = View.GONE
                    binding.includeHead.root.visibility = View.VISIBLE
//                    list.toMutableList().add(0, AirQuality("City", 0f, 0))
                    adapter?.submitList(list)
                }
            }
        })
    }
}