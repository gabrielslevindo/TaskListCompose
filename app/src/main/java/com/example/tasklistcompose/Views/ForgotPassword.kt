package com.example.tasklistcompose.Views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tasklistcompose.Components.ButtonCustom2Auth
import com.example.tasklistcompose.Listners.AuthListnners
import com.example.tasklistcompose.R
import com.example.tasklistcompose.ViewModel.AuthViewModel
import com.example.tasklistcompose.ui.theme.Dark_Blue
import com.example.tasklistcompose.ui.theme.Purple700
import com.example.tasklistcompose.ui.theme.ShapesEditText
import com.example.tasklistcompose.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassword(
    NavController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    var email by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()



    Scaffold(
        modifier = Modifier.background(
            brush = Brush.verticalGradient(colors = listOf(White, Dark_Blue))
        ),
        containerColor = Color.Transparent,

        ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {

            OutlinedTextField(

                value = email,
                onValueChange = {

                    email = it

                },
                label = {

                    Text(text = "E-mail")

                },
                maxLines = 1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = White,
                    focusedBorderColor = Purple700,
                    cursorColor = Purple700,
                    focusedLabelColor = Purple700
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
                shape = ShapesEditText.small,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.e_mail),
                        contentDescription = ""
                    )
                }
            )
            ButtonCustom2Auth(
                onClick = {


                    scope.launch(Dispatchers.IO) {


                        authViewModel.ForgotPassword(email, object : AuthListnners{
                            override fun onSucess(mensage: String, screen: String) {
                               Toast.makeText(context,mensage,Toast.LENGTH_LONG).show()
                                NavController.navigate(screen)
                            }

                            override fun onFalure(error: String) {
                                Toast.makeText(context,error,Toast.LENGTH_LONG).show()

                            }
                        })

                    }


                },
                Text = "Redefinir Senha."
            )
        }


    }


}