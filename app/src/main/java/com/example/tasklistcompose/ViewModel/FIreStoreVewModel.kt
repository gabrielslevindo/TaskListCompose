package com.example.tasklistcompose.ViewModel

import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasklistcompose.Data.Local.Task
import com.example.tasklistcompose.Repository.RepositoryFireStore
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FireStoreVewModel @Inject constructor(private val repositoryFireStore: RepositoryFireStore) :
    ViewModel() {


    private val _allTasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    private val allTasks: StateFlow<MutableList<Task>> = _allTasks


    // pra recuperar o nome.
    private val _name = MutableStateFlow("")
    private val name: StateFlow<String> = _name


    fun saveTask(task: String, description: String, priority: Int, checkTask: Boolean) {
        viewModelScope.launch {

            repositoryFireStore.saveTask(task, description, priority, checkTask)

        }


    }


    fun getTasks(): Flow<MutableList<Task>> {

        viewModelScope.launch {

            repositoryFireStore.getTasks().collect {

                _allTasks.value = it


            }

        }

        return allTasks

    }

    fun deleteTask(name: String) {

        viewModelScope.launch {

            repositoryFireStore.deleteTask(name)


        }


    }

    fun UpdateTask(task: String, checkTask: Boolean) {

        viewModelScope.launch {

            repositoryFireStore.updateTask(task, checkTask)

        }

    }

    // recuperar nome do usuario.
    fun profileUser(): Flow<String> {

        viewModelScope.launch {


            repositoryFireStore.profileUser().collect {


                _name.value = it


            }


        }


        return name
    }
}