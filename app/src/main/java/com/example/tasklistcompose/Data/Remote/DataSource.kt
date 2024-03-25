package com.example.tasklistcompose.Data.Remote

import com.example.tasklistcompose.Data.Local.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


// mudamos as regras do banco de dados para so fazer leitura e escrita medinte a login.


class DataSource @Inject constructor() {


    private val fireStore = FirebaseFirestore.getInstance()


    private val _allTasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    private val allTasks: StateFlow<MutableList<Task>> = _allTasks


    // pra recuperar o nome.
    private val _name = MutableStateFlow("")
    private val name: StateFlow<String> = _name


    fun saveTask(task: String, description: String, priority: Int, checkTask: Boolean) {

        // variável pra verificar user logado.
        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()

        val TaskMap = hashMapOf(

            "task" to task,
            "description" to description,
            "priority" to priority,
            "checkTask" to checkTask

        )
        // vou criar uma coleção task, que vai ser baseada no id da conta
        // e vai ter um subcoleção que sao as taskUser, salvando o domunto task.
        fireStore.collection("tasks").document(userId).collection("task_User").document(task)
            .set(TaskMap).addOnCompleteListener {}.addOnFailureListener {}

        /*fireStore.collection("tasks").document(task).set(TaskMap)
            .addOnCompleteListener {}
            .addOnFailureListener {}
         */

    }


    fun getTasks(): Flow<MutableList<Task>> {
        val taskList: MutableList<Task> = mutableListOf()
        // variável pra verificar user logado.
        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()


//primeira forma sem acessa subcoleção, depois so por os addcompleted e etc.
        //   fireStore.collection("tasks").get()

        fireStore.collection("tasks").document(userId).collection("task_User").get()
            .addOnCompleteListener {

                if (it.isSuccessful) {

                    for (document in it.result) {

                        val task =
                            document.toObject(Task::class.java)
                        taskList.add(task)
                        _allTasks.value = taskList
                    }
                }
            }
        return allTasks
    }


    fun deleteTask(task: String) {
        // variável pra verificar user logado.
        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()


        /*   fireStore.collection("tasks").document(task).delete()
               .addOnCompleteListener {}
               .addOnFailureListener {}
         */
        fireStore.collection("tasks").document(userId).collection("task_User").document(task)
            .delete()
            .addOnCompleteListener {}.addOnFailureListener {}
    }

    fun UpdateTask(task: String, checkTask: Boolean) {
        // variável pra verificar user logado.
        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()


        /*    fireStore.collection("tasks").document(task).update("checkTask", checkTask)
                .addOnCompleteListener { }
                .addOnFailureListener {}
         */
        fireStore.collection("tasks").document(userId).collection("task_User").document(task)
            .update("checkTask", checkTask)
            .addOnCompleteListener {}
            .addOnFailureListener {}

    }

    // recuperar nome ou campos de um db firestore.
    fun ProfileUser(): Flow<String> {
        // variável pra verificar user logado.
        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()


        fireStore.collection("Users").document(userId).get().addOnCompleteListener {


            if (it.isSuccessful) {

                val name = it.result.getString("name").toString()
                _name.value = name


            }

        }

        return name

    }


}