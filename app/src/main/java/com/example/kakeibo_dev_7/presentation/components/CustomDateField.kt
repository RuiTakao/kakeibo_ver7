package com.example.kakeibo_dev_7.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDateField(
    value: String,
    onValueChange: (String) -> Unit
) {

    var visible by remember { mutableStateOf(false) }
    val yMd = SimpleDateFormat("y年M月d日", Locale.JAPANESE)
    val setState = Date()
    val state = rememberDatePickerState(setState.time)

    Box(
        modifier = Modifier
            .fillMaxWidth()

            .height(32.dp)
            .background(
                color = Color(0xFFEFEFEF),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { visible = !visible }
            .padding(5.dp)

    ) {
        Text(text = yMd.format(Date()))
    }

    if (visible) {
        DatePickerDialog(
            onDismissRequest = { visible = false },
            confirmButton = { /*TODO*/ }
        ) {
            DatePicker(state = state)
        }
    }
}