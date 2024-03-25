package com.example.tasklistcompose.Views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tasklistcompose.Components.ButtonCustom
import com.example.tasklistcompose.Components.EditTextCustom
import com.example.tasklistcompose.Constants.Constants
import com.example.tasklistcompose.Data.Remote.DataSource
import com.example.tasklistcompose.Repository.RepositoryFireStore
import com.example.tasklistcompose.ViewModel.FireStoreVewModel
import com.example.tasklistcompose.ui.theme.Purple40
import com.example.tasklistcompose.ui.theme.White
import com.example.tasklistcompose.ui.theme.highGreen
import com.example.tasklistcompose.ui.theme.highRed
import com.example.tasklistcompose.ui.theme.highYellow
import com.example.tasklistcompose.ui.theme.ligthGreen
import com.example.tasklistcompose.ui.theme.ligthRed
import com.example.tasklistcompose.ui.theme.ligthYellow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListDetail(NavController: NavController, viewModel: FireStoreVewModel = hiltViewModel()) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    var Title by remember {
        mutableStateOf("")
    }
    var Description by remember {
        mutableStateOf("")
    }

    var noPriorityTask by remember {
        mutableStateOf(false)
    }
    var lowPriorityTask by remember {
        mutableStateOf(false)
    }
    var mediumPriorityTask by remember {
        mutableStateOf(false)
    }
    var highPriorityTask by remember {
        mutableStateOf(false)
    }



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
            })


        }) {

        Column(
            modifier =
            Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {

            EditTextCustom(
                value = Title,

                onValueChange =

                {
                    Title = it
                },

                label =
                {

                    Text(text = "Título")

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(


                    keyboardType = KeyboardType.Text

                )
            )
            EditTextCustom(
                value = Description,

                onValueChange =
                {

                    Description = it

                },
                label =
                {

                    Text(text = "Descrição")

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 10.dp, 20.dp, 10.dp),
                maxLines = 5,
                keyboardOptions = KeyboardOptions(

                    keyboardType = KeyboardType.Text

                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                Text(
                    text = "Nível de Prioridade:",
                    fontWeight = FontWeight.Bold,
                )
                RadioButton(

                    selected = lowPriorityTask,

                    onClick = {

                        lowPriorityTask = !lowPriorityTask

                    },
                    colors = RadioButtonDefaults.colors(

                        unselectedColor = ligthGreen,
                        selectedColor = highGreen


                    )

                )
                RadioButton(

                    selected = mediumPriorityTask,

                    onClick = {
                        mediumPriorityTask = !mediumPriorityTask


                    },
                    colors = RadioButtonDefaults.colors(

                        unselectedColor = ligthYellow,
                        selectedColor = highYellow


                    )

                )
                RadioButton(

                    selected = highPriorityTask,

                    onClick = {


                        highPriorityTask = !highPriorityTask

                    },
                    colors = RadioButtonDefaults.colors(

                        unselectedColor = ligthRed,
                        selectedColor = highRed


                    )

                )


            }


            ButtonCustom(
                onClick =
                {

                    var mesage = true

                    scope.launch(Dispatchers.IO) {


                        if (Title.isEmpty()) {
                            mesage = false
                        } else if (Title.isNotEmpty() && Description.isNotEmpty() && lowPriorityTask) {
                            viewModel.saveTask(Title, Description, Constants.LOW_PRIORITY, false)
                            mesage = true
                        } else if (Title.isNotEmpty() && Description.isNotEmpty() && mediumPriorityTask) {
                            viewModel.saveTask(Title, Description, Constants.MEDIUM_PRIORITY, false)
                            mesage = true
                        } else if (Title.isNotEmpty() && Description.isNotEmpty() && highPriorityTask) {
                            viewModel.saveTask(Title, Description, Constants.HIGH_PRIORITY, false)
                            mesage = true
                        } else if (Title.isNotEmpty() && Description.isNotEmpty() && noPriorityTask) {
                            viewModel.saveTask(Title, Description, Constants.NOT_PRIORITY, false)
                            mesage = true
                        } else if (Title.isNotEmpty() && noPriorityTask) {
                            viewModel.saveTask(Title, Description, Constants.NOT_PRIORITY, false)
                            mesage = true
                        } else if (Title.isNotEmpty() && lowPriorityTask) {
                            viewModel.saveTask(Title, Description, Constants.LOW_PRIORITY, false)
                            mesage = true
                        } else if (Title.isNotEmpty() && mediumPriorityTask) {
                            viewModel.saveTask(Title, Description, Constants.MEDIUM_PRIORITY, false)
                            mesage = true
                        } else if (Title.isNotEmpty() && highPriorityTask) {
                            viewModel.saveTask(Title, Description, Constants.HIGH_PRIORITY, false)
                            mesage = true
                        }

                    }

                    scope.launch(Dispatchers.Main) {


                        if (mesage) {


                            Toast.makeText(
                                context,
                                "Tarefa Salva com Sucesso.",
                                Toast.LENGTH_SHORT
                            )
                                .show()

                            NavController.popBackStack()


                        } else {


                            Toast.makeText(
                                context,
                                "Falha ao Salvar Tarefa.",
                                Toast.LENGTH_SHORT
                            )
                                .show()


                        }


                    }


                },
                Text = "Salvar",

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp)
            )


        }


    }


}

@Composable
@Preview
fun PreviewTaskDetail() {

    TaskListDetail(NavController = rememberNavController(), viewModel = hiltViewModel())

}