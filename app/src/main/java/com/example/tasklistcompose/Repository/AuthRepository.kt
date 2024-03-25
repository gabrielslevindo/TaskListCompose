package com.example.tasklistcompose.Repository

import com.example.tasklistcompose.Authentication.Auth
import com.example.tasklistcompose.Listners.AuthListnners
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class AuthRepository @Inject constructor(private val auth: Auth) {




    fun CreateUser(email: String, password: String, name: String, listnners: AuthListnners) {


        auth.CreateUser(email, password, name ,listnners)


    }

    fun LoginUser(email: String,password: String,listnners: AuthListnners){


        auth.LoginUser(email, password, listnners)

    }

    fun ForgotPassword(email: String,listnners: AuthListnners){


        auth.ForgotPassword(email, listnners)

    }

    fun CheckUser(): Flow<Boolean>{

    return  auth.CheckUser()


    }


}