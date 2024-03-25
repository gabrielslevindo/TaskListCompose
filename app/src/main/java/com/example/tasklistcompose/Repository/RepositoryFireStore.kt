package com.example.tasklistcompose.Repository

import com.example.tasklistcompose.Data.Local.Task
import com.example.tasklistcompose.Data.Remote.DataSource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class RepositoryFireStore @Inject constructor(private val dataSource: DataSource) {


    fun saveTask(task: String, description: String, priority: Int, checkTask: Boolean) {

        dataSource.saveTask(task, description, priority, checkTask)
    }

    fun getTasks(): Flow<MutableList<Task>> {

        return dataSource.getTasks()


    }

    fun deleteTask(name: String) {

        dataSource.deleteTask(name)

    }

    fun updateTask(task: String, checkTask: Boolean) {


        dataSource.UpdateTask(task, checkTask)


    }

    fun profileUser(): Flow<String> {

        return dataSource.ProfileUser()

    }


}