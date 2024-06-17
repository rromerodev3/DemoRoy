package com.example.demoroy.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demoroy.R
import com.example.demoroy.databinding.ActivityMainBinding
import com.example.demoroy.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val userAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUiBehavior()
        subscribeToEvents()
    }

    private fun setupUiBehavior() {
        binding.rclVwUsers.apply {
            setHasFixedSize(true)
            adapter = userAdapter
        }
        binding.btnGetUsers.setOnClickListener {
            viewModel.getUsers()
        }
    }

    private fun subscribeToEvents() {
        viewModel.isLoading.observe(this) { showLoading ->
            binding.loading = showLoading
//            binding.cnstLytProgress.visibility = if (showLoading) {
//                View.VISIBLE
//            } else {
//                View.INVISIBLE
//            }

        }
        viewModel.users.observe(this) {
            userAdapter.setUsers(it)
        }
    }
}