package com.example.tasklistcompose.Authentication

import com.example.tasklistcompose.Listners.AuthListnners
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.Flow
import javax.inject.Inject

class Auth @Inject constructor() {


    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()


    val _checkUser = MutableStateFlow<Boolean>(false)
    val checkUser: StateFlow<Boolean> = _checkUser


    fun CreateUser(email: String, password: String, name: String, listnners: AuthListnners) {

        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {


            listnners.onFalure("Preencha todos os campos.")


        } else {


            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {


                if (it.isSuccessful) {


                    // salvar o id do usuario. // salvar via id pois se ficar com o nome o documento pode duplicar e acabar sobreescrevendo o dado.
                    var id = FirebaseAuth.getInstance().currentUser?.uid.toString()
                    val nameMap = hashMapOf(
                        "name" to name,
                        "email" to email,
                        "id" to id
                    )

                    firestore.collection("Users").document(id).set(nameMap).addOnCompleteListener {


                        // colocamos o screen no on sucsses pois ai ele só navega pra tela de login se ele for cadastrado, casao contrario nao.
                        listnners.onSucess("Sucesso ao Cadastrar Usuário.", "SingInActivity")

                    }.addOnFailureListener {

                        listnners.onFalure("Server Error.")
                    }

                }
            }.addOnFailureListener {


                val errorMensage = when (it) {

                    is FirebaseAuthWeakPasswordException -> "Digite uma senha com pelo menos 6 cacacteres."
                    is FirebaseAuthInvalidCredentialsException -> "Digite um email válido."
                    is FirebaseAuthUserCollisionException -> "Este email já está em uso."
                    is FirebaseNetworkException -> "Sem conexão com a internet."
                    else -> "Erro ao cadastrar usuário."
                }

                listnners.onFalure(errorMensage)


            }
        }
    }


    fun LoginUser(email: String, password: String, listnners: AuthListnners){

        if (email.isEmpty() || password.isEmpty()) {

            listnners.onFalure("Preencha todos os campos..")

        } else {

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {

                    // colocamos o screen no on sucsses pois ai ele só navega pra tela de login se ele for cadastrado, casao contrario nao.
                    listnners.onSucess("Usuário Logado com Sucesso.", "TaskList")
                }

            }.addOnFailureListener {

                val errorMessage = when (it) {
                    is FirebaseAuthInvalidCredentialsException -> "E-mail ou Senha inválidos."
                    is FirebaseAuthInvalidUserException -> "E-mail inválido."
                    is FirebaseNetworkException -> "Sem conexão com a internet."
                    else -> "Erro de servidor."
                }
                listnners.onFalure(errorMessage)


            }
        }
    }



    fun ForgotPassword(email: String, listnners: AuthListnners){



        auth.sendPasswordResetEmail(email).addOnCompleteListener {


            listnners.onSucess("Verifique seu E-mail.","SingInActivity")

        }.addOnFailureListener{

            listnners.onFalure("Server Error.")

        }
    }

    // verificar usuario.
    fun CheckUser():kotlinx.coroutines.flow.Flow<Boolean>{

        // estamos observando se o usuario esta logado ou nao
        // se passar como false o boolean ele nao ta logado e se passar como true está
        // essa função é para fazer essa verificação.

      val loggedUser = FirebaseAuth.getInstance().currentUser

        _checkUser.value = loggedUser != null

        return checkUser



    }


}