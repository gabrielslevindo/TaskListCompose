package com.example.tasklistcompose.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasklistcompose.ui.theme.Dark_Blue
import com.example.tasklistcompose.ui.theme.Dark_Pink
import com.example.tasklistcompose.ui.theme.ShapesEditText
import com.example.tasklistcompose.ui.theme.White

@Composable
fun ButtonCustom2Auth(onClick: () -> Unit, Text: String) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(87.dp)
            .padding(20.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Dark_Blue,
                        Dark_Pink
                    )
                ),
                shape = ShapesEditText.medium
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            disabledElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        shape = ShapesEditText.medium,
        border = BorderStroke(2.dp, White)




        ) {
        androidx.compose.material3.Text(text = Text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = White)
    }


}