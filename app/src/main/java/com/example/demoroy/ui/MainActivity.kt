package com.example.demoroy.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demoroy.R
import com.example.demoroy.databinding.ActivityMainBinding
import com.example.demoroy.model.User
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
        binding.apply {
            composeView.setContent {
                DemoRoy(viewModel.composeUsers)
            }
        }
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUiBehavior()
        subscribeToEvents()
        // new feature
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

@Composable
fun DemoRoy(users: List<User>) {
    LazyColumn {
        items(users) { user ->
            ListElement(user = user)
        }
    }
}

@Composable
fun ListElement(user: User) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Name: ${user.name}",
            Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Email: ${user.email}",
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDemoRoy() {
    val users = listOf(
        User(1, "Roy", "Romers", "roy@roy.com.mx"),
        User(2, "Ray", "Romers", "ray@roy.com.mx"),
        User(3, "Rey", "Romers", "rey@roy.com.mx"),
    )
    DemoRoy(users)
}