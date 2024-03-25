package com.example.tasklistcompose.Views

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Visibility
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tasklistcompose.Data.Local.Task
import com.example.tasklistcompose.R
import com.example.tasklistcompose.ViewModel.FireStoreVewModel
import com.example.tasklistcompose.ui.theme.NotPriority
import com.example.tasklistcompose.ui.theme.Purple80
import com.example.tasklistcompose.ui.theme.ShapesEditText
import com.example.tasklistcompose.ui.theme.White
import com.example.tasklistcompose.ui.theme.highGreen
import com.example.tasklistcompose.ui.theme.highRed
import com.example.tasklistcompose.ui.theme.highYellow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ItemTask(

    navController: NavController,
    taskList: MutableList<Task>,
    position: Int,
    context: Context,
    viewModel: FireStoreVewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val Title = taskList[position].task
    val Descriptiom = taskList[position].description
    val Prioriry = taskList[position].priority
    val taskSucsess = taskList[position].checkTask

    var isCheckd by remember {

        mutableStateOf(taskSucsess)
    }


    fun AlertDialogDeleteTask() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deseja Deletar tarefa?")
            .setMessage("Tem Certeza?")
        alertDialog.setPositiveButton("Sim") { _, _ ->

            viewModel.deleteTask(Title.toString())
            scope.launch(Dispatchers.Main) {

                taskList.removeAt(position)
                navController.navigate("TaskList")
                Toast.makeText(context, "Tarefa excluida com sucesso.", Toast.LENGTH_SHORT).show()

            }
        }
        alertDialog.setNegativeButton("Não") { _, _ -> }
        alertDialog.show()
    }


    var PriorityLevel: String = when (Prioriry) {


        0 -> {
            "Sem Prioridade."
        }

        1 -> {
            "Prioridade Baixa."
        }

        2 -> {
            "Prioridade Média."
        }

        else -> {
            "Prioridade Alta."
        }
    }

    val PriorityColor = when (Prioriry) {

        0 -> NotPriority
        1 -> highGreen
        2 -> highYellow


        else -> highRed
    }



    Card(

        colors = CardDefaults.cardColors(

            containerColor = White,
            contentColor = White
        ),
        elevation = CardDefaults.cardElevation(

            defaultElevation = 10.dp

        ),
        shape = ShapesEditText.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 20.dp, 10.dp, 10.dp)


    ) {


        ConstraintLayout(

            modifier = Modifier.padding(20.dp)


        ) {

            val (title, descricption, priority, txtPriority, delete, checkBox) = createRefs()



            Text(text = "$Title",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.constrainAs(title) {


                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)

                })
            Text(text = "$Descriptiom",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.constrainAs(descricption) {


                    top.linkTo(title.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)


                }
            )
            Text(text = PriorityLevel,
                fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier.constrainAs(txtPriority) {


                    top.linkTo(descricption.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)


                }
            )
            Card(

                colors = CardDefaults.cardColors(

                    containerColor = PriorityColor,
                    contentColor = PriorityColor
                ),
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(priority) {

                        start.linkTo(txtPriority.end, margin = 10.dp)
                        top.linkTo(descricption.bottom, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                shape = ShapesEditText.large

            ) {}
            IconButton(onClick = {

                AlertDialogDeleteTask()

            },

                modifier = Modifier.constrainAs(delete) {

                    start.linkTo(priority.end, margin = 10.dp)
                    top.linkTo(descricption.bottom, margin = 10.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)


                }


            ) {

                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_delete_24),
                    contentDescription = "delete contac"
                )

            }
            Checkbox(

                checked = isCheckd!!,

                onCheckedChange = {

                    isCheckd = it
                    if(isCheckd == true){

                        Visibility.Gone

                    }
                      scope.launch (Dispatchers.IO){

                        if (isCheckd!!) {
                            viewModel.UpdateTask(Title!!, true)
                        } else {
                          viewModel.UpdateTask(Title!!, false)
                        }

                     }


                },
                modifier = Modifier.constrainAs(checkBox) {


                    start.linkTo(delete.end, margin = 10.dp)
                    top.linkTo(descricption.bottom, margin = 10.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)


                },
                colors = CheckboxDefaults.colors(

                    checkedColor = Purple80,
                    uncheckedColor = Color.Black

                )
            )

        }


    }


}

@Preview
@Composable
fun previewItem() {

    // ItemTask(navController = rememberNavController())


}