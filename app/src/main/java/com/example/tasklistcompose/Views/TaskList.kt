package com.example.tasklistcompose.Views

import android.app.AlertDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tasklistcompose.R
import com.example.tasklistcompose.ViewModel.AuthViewModel
import com.example.tasklistcompose.ViewModel.FireStoreVewModel
import com.example.tasklistcompose.ui.theme.Purple40
import com.example.tasklistcompose.ui.theme.White
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(
    NavController: NavHostController,
    viewModel: FireStoreVewModel = hiltViewModel()
) {

    val TaskList = viewModel.getTasks().collectAsState(mutableListOf()).value
    val context = LocalContext.current

   //recuperar nome user no top app bar.
    val nameUser = viewModel.profileUser().collectAsState(initial = "").value


    Scaffold(


        topBar = {

            TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(

                containerColor = Purple40


            ), title = {

                Text(
                    text = "Task List",
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            },
                // uso o action para conseguir por mais de 1 função no tool bar.
                actions = {

                TextButton(onClick = {





                }) {

                    Text(
                        // variavel que criei recuperando os seus nomes.
                        text = nameUser,
                        color = White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                TextButton(onClick = {

                    // btn deslogar user.
                    val alertDialog = AlertDialog.Builder(context)
                    alertDialog.setTitle("Deslogar")
                    alertDialog.setMessage("DESEJA SAIR DA SUA CONTA?")
                    alertDialog.setPositiveButton("Sim") { _, _ ->

                        FirebaseAuth.getInstance().signOut()
                        NavController.navigate("SingInActivity")

                    }
                    alertDialog.setNegativeButton("Não") { _, _ -> }
                    alertDialog.show()


                }) {

                    Text(
                        text = "SAIR",
                        color = White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }


            }


            )


        }, floatingActionButton = {

            FloatingActionButton(
                onClick = {


                    NavController.navigate("TaskListDetail")


                },
                containerColor = Purple40,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
                shape = CircleShape
            ) {

                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_add_24),
                    contentDescription = "Save Icon"
                )

            }


        }


    ) {

        Column(
            modifier =
            Modifier
                .padding(it)

        ) {


            LazyColumn() {


                itemsIndexed(TaskList) { position, _ ->

                    ItemTask(
                        navController = NavController,
                        taskList = TaskList,
                        position = position,
                        context = context,
                        viewModel = viewModel
                    )


                }


            }


        }


    }


}

