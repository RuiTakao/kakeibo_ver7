package com.example.kakeibo_dev_7.presentation.add_income_expenditure

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kakeibo_dev_7.common.IncomeExpenditureColor
import com.example.kakeibo_dev_7.common.IncomeExpenditureStatus
import com.example.kakeibo_dev_7.domain.model.Category
import com.example.kakeibo_dev_7.presentation.NavigationViewModel
import com.example.kakeibo_dev_7.presentation.components.layouts.Default
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@Composable
fun AddIncomeExpenditureScreen(
    navController: NavController,
    navigationViewModel: NavigationViewModel,
    viewModel: AddIncomeExpenditureViewModel = hiltViewModel()
) {

    val incomeExpenditure by viewModel.incomeExpenditure.collectAsState()

    val selectOptionText = remember { mutableStateOf("カテゴリーを選択してください") }

    Default(navController = navController, viewModel = navigationViewModel) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            color = Color.Gray,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 1.dp.toPx()
                        )
                    }
            ) {

                val expenditureStatus =
                    viewModel.incomeExpenditureStatus.value === IncomeExpenditureStatus.Expenditure
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .drawBehind {
                            drawLine(
                                color = if (expenditureStatus) Color(IncomeExpenditureColor.expenditureColor) else Color.Transparent,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 2.dp.toPx()
                            )
                        }
                        .clickable {
                            viewModel.incomeExpenditureStatus.value =
                                IncomeExpenditureStatus.Expenditure
                            selectOptionText.value = "カテゴリーを選択してください"
                        }
                ) {
                    Text(
                        text = "支出",
                        fontSize = 16.sp,
                        fontWeight = if (expenditureStatus) FontWeight.Bold else FontWeight.Medium,
                        color = if (expenditureStatus) Color(IncomeExpenditureColor.expenditureColor) else Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )
                }

                val incomeStatus =
                    viewModel.incomeExpenditureStatus.value === IncomeExpenditureStatus.Income
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawLine(
                                color = if (incomeStatus) Color(IncomeExpenditureColor.incomeColor) else Color.Transparent,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 2.dp.toPx()
                            )
                        }
                        .clickable {
                            viewModel.incomeExpenditureStatus.value = IncomeExpenditureStatus.Income
                            selectOptionText.value = "カテゴリーを選択してください"
                        }
                ) {
                    Text(
                        text = "収入",
                        fontSize = 16.sp,
                        fontWeight = if (incomeStatus) FontWeight.Bold else FontWeight.Medium,
                        color = if (incomeStatus) Color(IncomeExpenditureColor.incomeColor) else Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 16.dp)
            ) {

                Column {

                    // 日付
                    InputDateField(viewModel)

                    // 支出・収入
                    val label = when (viewModel.incomeExpenditureStatus.value) {
                        IncomeExpenditureStatus.Income -> "収入"
                        IncomeExpenditureStatus.Expenditure -> "支出"
                    }
                    InputPayField(label = label, viewModel = viewModel)

                    // カテゴリー
                    val categories by viewModel.category.collectAsState()
                    InputCategoryField(categories, selectOptionText, viewModel)

                    // 内容
                    InputContentField(viewModel)
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {

                    val buttonWidth =
                        LocalConfiguration.current.screenWidthDp / 2 - LocalConfiguration.current.screenWidthDp / 100 * 5

                    val buttonColor = Color(
                        when (viewModel.incomeExpenditureStatus.value) {
                            IncomeExpenditureStatus.Income -> IncomeExpenditureColor.incomeColor
                            IncomeExpenditureStatus.Expenditure -> IncomeExpenditureColor.expenditureColor
                        }
                    )

                    TextButton(
                        onClick = {
                            viewModel.create()
                        },
                        modifier = Modifier
                            .background(
                                color = buttonColor,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .width((buttonWidth).dp)
                            .height(40.dp)
                    ) {
                        Text(
                            text = "保存",
                            color = Color(0xFFFFFFFF)
                        )
                    }
                    TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = buttonColor,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .width((buttonWidth).dp)
                            .height(40.dp)
                    ) {
                        Text(
                            text = "続けて保存",
                            color = buttonColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InputContentField(viewModel: AddIncomeExpenditureViewModel) {

    var text by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = "内容",
            modifier = Modifier.fillMaxWidth(0.3f)
        )
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                viewModel.content = it
            },
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .background(
                            color = Color(0xFFEFEFEF),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(5.dp)
                ) {
                    innerTextField()
                }
            }
        )
    }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black)
    )
}

@Composable
fun InputPayField(label: String, viewModel: AddIncomeExpenditureViewModel) {

    var text by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.fillMaxWidth(0.3f)
        )
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                viewModel.price = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .background(
                            color = Color(0xFFEFEFEF),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(5.dp)
                ) {
                    innerTextField()
                }
            }
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDateField(viewModel: AddIncomeExpenditureViewModel) {

    var visible by remember { mutableStateOf(false) }
    val yMd = SimpleDateFormat("y年M月d日", Locale.JAPANESE)
    var setState by remember {
        mutableStateOf(Date())
    }
    val state = rememberDatePickerState(setState.time)
    val getDate = state.selectedDateMillis?.let {
        Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = "日付",
            modifier = Modifier.fillMaxWidth(0.3f)
        )

        Box(
            contentAlignment = Alignment.CenterStart,
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
            Text(text = yMd.format(setState))
        }

        if (visible) {

            val setState = viewModel.payDate.let { viewModel.payDate.toDate() } ?: Date()
            val state = rememberDatePickerState(setState.time)
            val getDate = state.selectedDateMillis?.let {
                Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
            }

            DatePickerDialog(
                onDismissRequest = { visible = false },
                confirmButton = {
                    Row {
                        TextButton(
                            onClick = { visible = false },
                            content = { Text(text = "キャンセル") }
                        )
                        TextButton(
                            onClick = {
                                visible = false

                                // 選択した日付をViewModelに保存
                                viewModel.payDate =
                                    getDate?.let {
                                        getDate.format(
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd 12:00:00")
                                        )
                                    }
                                        ?: if (viewModel.payDate == "") {
                                            LocalDate.now()
                                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd 12:00:00"))
                                        } else {
                                            val localDate = LocalDate.parse(
                                                viewModel.payDate,
                                                DateTimeFormatter.ofPattern("yyyy-MM-dd 12:00:00")
                                            )
                                            localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 12:00:00"))
                                        }
                            },
                            content = { Text(text = "OK") }
                        )
                    }
                }
            ) {
                DatePicker(state = state)
            }
        }
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black)
    )
}

@Composable
fun InputCategoryField(
    categoryList: List<Category>,
    selectOptionText: MutableState<String>,
    viewModel: AddIncomeExpenditureViewModel
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = "カテゴリー",
            modifier = Modifier.fillMaxWidth(0.3f)
        )
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .background(
                    color = Color(0xFFEFEFEF),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable { expanded = !expanded }
                .padding(5.dp)

        ) {
            Text(text = selectOptionText.value)
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "選択アイコン",
                modifier = Modifier.align(Alignment.CenterEnd)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                categoryList.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(text = it.name)
                        },
                        onClick = {
                            expanded = false

                            selectOptionText.value = it.name

                            viewModel.categoryId = it.id
                        }
                    )
                    // 区切り線
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )
                }
            }
        }

    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black)
    )
}

fun String.toDate(pattern: String = "yyyy-MM-dd HH:mm:ss"): Date? {
    val format = try {
        SimpleDateFormat(pattern, Locale.JAPANESE)
    } catch (e: IllegalArgumentException) {
        null
    }
    val date = format?.let {
        try {
            it.parse(this)
        } catch (e: ParseException) {
            null
        }
    }
    return date
}