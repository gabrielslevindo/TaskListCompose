package com.example.tasklistcompose.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasklistcompose.Listners.AuthListnners
import com.example.tasklistcompose.Repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {




   fun CreateUser(email: String, password: String, name:String ,listnners: AuthListnners) {


        viewModelScope.launch {


            authRepository.CreateUser(email, password,name ,listnners)


        }


    }

    fun LoginUser(email: String, password: String, listnners: AuthListnners){

        viewModelScope.launch {

            authRepository.LoginUser(email, password, listnners)

        }
    }

    fun ForgotPassword(email: String, listnners: AuthListnners){

        viewModelScope.launch {

            authRepository.ForgotPassword(email, listnners)


        }
    }
    fun CheckUser(): Flow<Boolean>{


        return  authRepository.CheckUser()

    }


}