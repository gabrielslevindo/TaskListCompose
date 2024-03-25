package com.example.tasklistcompose.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasklistcompose.ui.theme.LigthBlue
import com.example.tasklistcompose.ui.theme.Purple80
import com.example.tasklistcompose.ui.theme.ShapesEditText
import com.example.tasklistcompose.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextCustom(

    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    maxLines: Int,
    keyboardOptions: KeyboardOptions


) {

    OutlinedTextField(

        value = value, onValueChange = onValueChange,
        label = label,
        modifier = modifier,
        maxLines = maxLines,
        colors = TextFieldDefaults.outlinedTextFieldColors(

            focusedBorderColor = Purple80,
            focusedLabelColor = Purple80,
            containerColor = White,
            cursorColor = Purple80


        ),
        shape = ShapesEditText.small,
        keyboardOptions = keyboardOptions


    )


}

@Composable
@Preview
fun previewtv() {


    EditTextCustom(
        value = "Gabriel",
        onValueChange = {},
        label = { Text(text = "nome") },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 4,
        keyboardOptions = KeyboardOptions()
    )


}