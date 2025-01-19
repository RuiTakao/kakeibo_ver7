package com.example.kakeibo_dev_7.presentation.components.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kakeibo_dev_7.R
import com.example.kakeibo_dev_7.common.BottomNavigationStatus
import com.example.kakeibo_dev_7.common.IncomeExpenditureColor
import com.example.kakeibo_dev_7.presentation.NavigationViewModel
import com.example.kakeibo_dev_7.presentation.ScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Default(
    navController: NavController,
    viewModel: NavigationViewModel,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "収支入力")
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .padding(0.dp)
                    .height(70.dp),
                containerColor = Color(0xFFEFEFEF)
            ) {

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        viewModel.bottomNavigationStatus.value =
                            BottomNavigationStatus.AddIncomeExpenditure
                        navController.navigate(route = ScreenRoute.AddIncomeExpenditureScreen.route)
                    },
                    icon = {
                        Column {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "編集",
                                tint =
                                if (viewModel.bottomNavigationStatus.value == BottomNavigationStatus.AddIncomeExpenditure) Color(
                                    IncomeExpenditureColor.expenditureColor
                                ) else Color.Gray
                            )
                            if (viewModel.bottomNavigationStatus.value == BottomNavigationStatus.AddIncomeExpenditure) {
                                Text(
                                    text = "編集",
                                    fontSize = 14.sp,
                                    color = Color(IncomeExpenditureColor.expenditureColor)
                                )
                            }
                        }

                    },
                    enabled = viewModel.bottomNavigationStatus.value != BottomNavigationStatus.AddIncomeExpenditure
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        viewModel.bottomNavigationStatus.value =
                            BottomNavigationStatus.ExpenditureReport
                        navController.navigate(route = ScreenRoute.ExpenditureReportScreen.route)
                    },
                    icon = {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.expenditure_icon),
                                contentDescription = "支出",
                                colorFilter = ColorFilter.tint(
                                    if (viewModel.bottomNavigationStatus.value == BottomNavigationStatus.ExpenditureReport) Color(
                                        IncomeExpenditureColor.expenditureColor
                                    ) else Color.Gray
                                )
                            )
                            if (viewModel.bottomNavigationStatus.value == BottomNavigationStatus.ExpenditureReport) {
                                Text(
                                    text = "支出",
                                    fontSize = 14.sp,
                                    color = Color(IncomeExpenditureColor.expenditureColor)
                                )
                            }
                        }
                    },
                    enabled = viewModel.bottomNavigationStatus.value != BottomNavigationStatus.ExpenditureReport
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        viewModel.bottomNavigationStatus.value = BottomNavigationStatus.IncomeReport
                        navController.navigate(route = ScreenRoute.IncomeReportScreen.route)
                    },
                    icon = {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.income_icon),
                                contentDescription = "収入",
                                colorFilter = ColorFilter.tint(
                                    if (viewModel.bottomNavigationStatus.value == BottomNavigationStatus.IncomeReport) Color(
                                        IncomeExpenditureColor.incomeColor
                                    ) else Color.Gray
                                )
                            )
                            if (viewModel.bottomNavigationStatus.value == BottomNavigationStatus.IncomeReport) {
                                Text(
                                    text = "支出",
                                    fontSize = 14.sp,
                                    color = Color(IncomeExpenditureColor.incomeColor)
                                )
                            }
                        }
                    },
                    enabled = viewModel.bottomNavigationStatus.value != BottomNavigationStatus.IncomeReport
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        viewModel.bottomNavigationStatus.value =
                            BottomNavigationStatus.TransactionMoneyReport
                        navController.navigate(route = ScreenRoute.TransactionMoneyReportScreen.route)
                    },
                    icon = {
                        Column {
                            Icon(
                                imageVector = Icons.Filled.ReceiptLong,
                                contentDescription = "明細",
                                tint = if (viewModel.bottomNavigationStatus.value == BottomNavigationStatus.TransactionMoneyReport) Color(
                                    IncomeExpenditureColor.expenditureColor
                                ) else Color.Gray
                            )
                            if (viewModel.bottomNavigationStatus.value == BottomNavigationStatus.TransactionMoneyReport) {
                                Text(
                                    text = "明細",
                                    fontSize = 14.sp,
                                    color = Color(IncomeExpenditureColor.expenditureColor)
                                )
                            }
                        }
                    },
                    enabled = viewModel.bottomNavigationStatus.value != BottomNavigationStatus.TransactionMoneyReport
                )
                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = "設定",
                        tint = Color.Gray
                    )
                })
            }
        },
        content = { content(it) }
    )
}