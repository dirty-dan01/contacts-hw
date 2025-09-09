package com.example.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contacts.data.User
import com.example.contacts.repo.ServiceHandler
import com.example.contacts.viewmodel.UiState
import com.example.contacts.viewmodel.UserViewModel
import com.example.contacts.viewmodel.UserViewModelFactory
class MainActivity : ComponentActivity() {

    private val viewModel : UserViewModel by viewModels {
        UserViewModelFactory(ServiceHandler.userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                UserListApp(viewModel)
            }
        }
    }
}
@Composable
fun UserListApp(viewModel: UserViewModel) {
    // Provide Retrofit + Repository + ViewModel
    val state by viewModel.state.collectAsState()
    when (val s = state) {
        is UiState.Loading -> Text("Loading...")
        is UiState.Error -> Text(s.message)
        is UiState.Success -> UserList(s.users)
    }
}
@Composable
fun UserList(users: List<User>) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(users, key = {it.id}) { user ->
            Text(text = "${user.name} - ${user.email}")
            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
        }
    }
}