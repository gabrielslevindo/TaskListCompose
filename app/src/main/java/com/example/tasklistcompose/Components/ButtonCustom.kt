package com.example.tasklistcompose.Components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.tasklistcompose.ui.theme.LigthBlue
import com.example.tasklistcompose.ui.theme.ShapesEditText
import com.example.tasklistcompose.ui.theme.White

@Composable
fun ButtonCustom(onClick: () -> Unit, Text: String, modifier: Modifier) {


    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(

            containerColor = LigthBlue,
            contentColor = White

        ),
        shape = ShapesEditText.small


    ) {

        androidx.compose.material3.Text(
            text = Text, fontSize = 18.sp, fontWeight = FontWeight.Bold
        )


    }


}

@Composable
@Preview
fun PreviewBtn (){


    ButtonCustom(onClick = { /*TODO*/ }, Text = "", modifier = Modifier)


}