package com.example.tasklistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasklistcompose.ViewModel.AuthViewModel
import com.example.tasklistcompose.ViewModel.FireStoreVewModel
import com.example.tasklistcompose.Views.ForgotPassword
import com.example.tasklistcompose.Views.SingInActivity
import com.example.tasklistcompose.Views.SingUpScreen
import com.example.tasklistcompose.Views.TaskList
import com.example.tasklistcompose.Views.TaskListDetail
import com.example.tasklistcompose.ui.theme.TaskListComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskListComposeTheme {


                val NavController = rememberNavController()
                val viewModel: FireStoreVewModel = hiltViewModel()
                val authViewModel: AuthViewModel = hiltViewModel()


                NavHost(navController = NavController, startDestination = "SingInActivity") {


                    composable("TaskList") {

                        TaskList(NavController, viewModel)


                    }
                    composable("TaskListDetail") {

                        TaskListDetail(NavController, viewModel)


                    }
                    composable("SingInActivity") {

                        SingInActivity(NavController, authViewModel)


                    }
                    composable("SingUpScreen") {

                        SingUpScreen(NavController, authViewModel)


                    }
                    composable("ForgotPassword") {

                        ForgotPassword(NavController, authViewModel)


                    }


                }


            }
        }
    }
}








