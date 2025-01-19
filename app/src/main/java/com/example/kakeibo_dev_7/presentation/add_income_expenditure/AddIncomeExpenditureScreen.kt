package com.example.kakeibo_dev_7.presentation.add_income_expenditure

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kakeibo_dev_7.common.IncomeExpenditureColor
import com.example.kakeibo_dev_7.common.IncomeExpenditureStatus
import com.example.kakeibo_dev_7.presentation.components.CustomDateField
import com.example.kakeibo_dev_7.presentation.components.CustomTextField
import com.example.kakeibo_dev_7.presentation.components.layouts.Default

@Composable
fun AddIncomeExpenditureScreen(
    navController: NavController,
    viewModel: AddIncomeExpenditureViewModel = hiltViewModel()
) {

    val text = remember { mutableStateOf("") }
    Default(navController = navController) { paddingValues ->

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
                        CustomDateField(
                            value = text.value,
                            onValueChange = { text.value = it }
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Black)
                    )
                    val inputText = when (viewModel.incomeExpenditureStatus.value) {
                            IncomeExpenditureStatus.Income -> "収入"
                            IncomeExpenditureStatus.Expenditure -> "支出"
                        }
                    InputField(text = text, label = inputText)
                    InputField(text = text, label = "カテゴリー")
                    InputField(text = text, label = "内容")
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
                        onClick = { /*TODO*/ },
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
fun InputField(text: MutableState<String>, label: String) {
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
        CustomTextField(
            value = text.value,
            onValueChange = { text.value = it }
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black)
    )
}
