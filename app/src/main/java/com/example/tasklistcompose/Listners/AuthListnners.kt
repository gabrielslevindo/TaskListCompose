package com.example.tasklistcompose.Listners

interface AuthListnners {


    fun onSucess(mensage: String, screen: String)
    fun onFalure(error: String)


}