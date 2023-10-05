package com.tunghoang.mvvm_sample.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunghoang.mvvm_sample.databinding.ActivityMainBinding
import com.tunghoang.mvvm_sample.repository.EventRepository
import com.tunghoang.mvvm_sample.ui.adapter.EventAdapter
import com.tunghoang.mvvm_sample.util.Resource
import com.tunghoang.mvvm_sample.viewmodel.EventViewModel
import com.tunghoang.mvvm_sample.viewmodel.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var eventViewModel: EventViewModel
    private lateinit var eventAdapter: EventAdapter
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvEvent.layoutManager = LinearLayoutManager(this)
        eventAdapter = EventAdapter()
        binding.rvEvent.adapter = eventAdapter

        setupViewModel()
        eventViewModel.getEvents("")

    }

    private fun setupViewModel() {
        val repository = EventRepository()
        val factory = ViewModelProviderFactory(application, repository)
        eventViewModel = ViewModelProvider(this, factory)[EventViewModel::class.java]

        eventViewModel.eventsData.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { data ->
                        eventAdapter.differ.submitList(data)
                        eventAdapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.d("APILOG", "request error: ${message}")
                    }
                }
                is Resource.Loading -> {
                    Log.d("APILOG", "loading")
                }
            }
        }
    }
}